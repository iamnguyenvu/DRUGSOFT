Create Database DRUGSOFT
USE DRUGSOFT

USE master;
ALTER DATABASE [DRUGSOFT] SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
DROP DATABASE [DRUGSOFT];


Create Table TaiKhoan
(
	tenDangNhap varchar(16) not null check(LEN(tenDangNhap) Between 6 and 16) Primary Key,        ---Tên đăng nhập là khóa chính và <=6 tenDangNhap <= 16 ký tự
	matKhau varchar(16) not null check(
		LEN(matKhau) Between 8 and 16
		And Patindex('%[0-9]%', matKhau) > 0
		And Patindex('%[A-Z]%', matKhau) > 0
		And Patindex('%[a-z]%', matKhau) > 0
		And Patindex('%[!@#$%^&*(),.?":{}|<>]%', matKhau) > 0
	),
	phanQuyen Bit not null,
	trangThai Bit not null
)

--Tạo bảng Loại nhân viên
Create Table LoaiNhanVien
(
	maLoaiNV VarChar(5) Not null Primary Key,
	tenLoaiNV Nvarchar(50) Not Null
)

--Ràng buộc maLoaiNV trong bảng Loại Nhân viên : GỒM 2 LOẠI: NV(NHÂN VIÊN) VÀ QL(QUẢN LÝ)
ALTER TABLE LoaiNhanVien
ADD CONSTRAINT chk_maLoaiNV CHECK (maLoaiNV IN ('NV', 'QL'));


CREATE TABLE NhanVien (
    maNV Char(10) Not null Primary Key,      -- Mã nhân viên, định dạng NVYYMMRRRR
	hoNV Nvarchar(20) Not Null,
    tenNV Nvarchar(10) Not Null,   -- Tên nhân viên
	gioiTinh Nvarchar (5) Not Null,   -- Giới Tính
    sdt Nvarchar(10) Not Null,       -- Số điện thoại
    cccd Char(12) Not Null,      -- Căn cước công dân
    chucVu Nvarchar(20) Not Null,   -- Chức vụ
    diaChi Nvarchar(255) Not null,           -- Địa chỉ
    ngaySinh Date NOT NULL,         -- Ngày sinh (LocalDate tương ứng với kiểu DATE)
    trangThai BIT NOT NULL,          -- Trạng thái (0: Đã nghỉ việc, 1: Đang làm)
	ngayVaoLam Date Not Null,
	hinhAnhNV NVARCHAR(255) NOT NULL
);

--Thêm trường tenDangNhap vào bảng NhanVien
Alter Table NhanVien
Add tenDangNhap varchar(16) not null;
--set Khóa ngoại cho bảng NhanVien
ALTER TABLE NhanVien
ADD CONSTRAINT FK_TaiKhoan_NhanVien
FOREIGN KEY (tenDangNhap) REFERENCES TaiKhoan(tenDangNhap);

--Ràng buộc giới tính
ALTER TABLE NhanVien
ADD CONSTRAINT chk_gioiTinh CHECK (gioiTinh IN (N'Nam', N'Nữ', N'Khác'))
-- ràng buộc sdt
ALTER TABLE NhanVien
ADD CONSTRAINT chk_sdt CHECK (
    SDT IS NOT NULL AND                           -- Không được để trống
    LEN(SDT) = 10 AND                             -- Độ dài phải là 10 ký tự
    SDT NOT LIKE '%[^0-9]%' AND                   -- Chỉ chứa ký tự số
    (SDT LIKE '03%' OR SDT LIKE '05%' OR         -- Bắt đầu bằng 03, 05, 07, hoặc 09
     SDT LIKE '07%' OR SDT LIKE '09%')
);
--Ràng buộc cccd
ALTER TABLE NhanVien
ADD CONSTRAINT chk_cccd CHECK (cccd NOT LIKE '%[^0-9]%');



--Ràng buộc chức vụ chỉ nhập được NhanVien hoặc Quanly
ALTER TABLE NhanVien
ADD CONSTRAINT chk_chucVu CHECK (chucVu IN ('Nhanvien', 'Quanly'));

--Ràng buộc ngày sinh phải đủ 18 tuổi 
ALTER TABLE NhanVien
ADD CONSTRAINT chk_ngaySinh CHECK (ngaySinh <= DATEADD(YEAR, -18, GETDATE()));

--Ràng buộc ngày vào làm không vượt quá ngày hiện tại
ALTER TABLE NhanVien
ADD CONSTRAINT CK_ngayVaoLam CHECK (ngayVaoLam <= GETDATE());


--Thêm trường maLoaiNV VÀO BẢNG NhanVien
ALTER TABLE NhanVien
ADD maLoaiNV VarChar(5) Not Null;

--Ràng buộc khóa ngoại maLoaiNV cho bảng NhanVien
ALTER TABLE NhanVien
ADD CONSTRAINT FK_LoaiNhanVien_NhanVien
FOREIGN KEY (maLoaiNV) REFERENCES LoaiNhanVien(maLoaiNV);

--TRIGGER NHÂN VIÊN
CREATE TRIGGER trg_InsertNhanVien
ON NhanVien
INSTEAD OF INSERT
AS
BEGIN
    -- Kiểm tra các điều kiện
    DECLARE @maNV CHAR(10), @ngayVaoLam DATE, @tenDangNhap VARCHAR(16), @hinhAnhNV VARCHAR(255);
    
    SELECT 
        @maNV = maNV, 
        @ngayVaoLam = ngayVaoLam, 
        @tenDangNhap = tenDangNhap,
        @hinhAnhNV = hinhAnhNV  -- Lấy giá trị từ bảng inserted
    FROM inserted;

    -- Kiểm tra điều kiện cho maNV
    IF NOT (
        @maNV LIKE 'NV[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'  -- Định dạng NVYYMMRRRR
        AND SUBSTRING(@maNV, 3, 2) = RIGHT(CONVERT(VARCHAR(4), YEAR(@ngayVaoLam)), 2)  -- YY
        AND SUBSTRING(@maNV, 5, 2) = RIGHT('0' + CAST(MONTH(@ngayVaoLam) AS NVARCHAR), 2)  -- MM
        AND ISNUMERIC(SUBSTRING(@maNV, 7, 4)) = 1  -- RRRR phải là số
        AND LEN(SUBSTRING(@maNV, 7, 4)) = 4         -- Đảm bảo RRRR có độ dài 4
    )
    BEGIN
        RAISERROR('Mã nhân viên không hợp lệ!', 16, 1);
        ROLLBACK TRANSACTION;
        RETURN;
    END

    -- Kiểm tra điều kiện cho ngày vào làm
    IF NOT (@ngayVaoLam <= GETDATE())
    BEGIN
        RAISERROR('Ngày vào làm không hợp lệ!', 16, 1);
        ROLLBACK TRANSACTION;
        RETURN;
    END

    -- Kiểm tra điều kiện cho hinhAnhNV
    IF NOT (
        @hinhAnhNV LIKE '%.png' OR @hinhAnhNV LIKE '%.jpg'
    )
    BEGIN
        RAISERROR('Đường dẫn hình ảnh không hợp lệ!', 16, 1);
        ROLLBACK TRANSACTION;
        RETURN;
    END

    -- Nếu tất cả các điều kiện đều hợp lệ, thực hiện thêm bản ghi vào bảng NhanVien
    INSERT INTO NhanVien (maNV, hoNV, tenNV,gioiTinh, sdt, cccd, chucVu, diaChi, ngaySinh, trangThai, ngayVaoLam, hinhAnhNV, tenDangNhap, maLoaiNV)
    SELECT maNV, hoNV, tenNV,gioiTinh, sdt, cccd, chucVu, diaChi, ngaySinh, trangThai, ngayVaoLam, hinhAnhNV, tenDangNhap, maLoaiNV
    FROM inserted;
END;

--Tạo bảng Khách Hàng
CREATE TABLE KhachHang (
    maKH CHAR(13) NOT NULL PRIMARY KEY,  -- Mã khách hàng theo định dạng KHYYMMddRRRRR
    tenKH NVARCHAR(50) NOT NULL,         -- Tên khách hàng (String)
    SDT CHAR(10) NOT NULL,           -- Số điện thoại (String)
    diemThuong INT NOT NULL DEFAULT 0,    -- Điểm thưởng (int)
    gioiTinh NVARCHAR(10) NOT NULL       -- Giới tính (String)
);

-- Trigger kiểm tra ràng buộc khi thêm dữ liệu vào bảng KhachHang
CREATE TRIGGER trg_CheckKhachHang
ON KhachHang
INSTEAD OF INSERT
AS
BEGIN
    -- Khai báo các biến để lưu trữ giá trị
    DECLARE @maKH CHAR(13), @tenKH NVARCHAR(50), @SDT CHAR(10), @diemThuong INT, @gioiTinh NVARCHAR(10);

    -- Lấy ngày, tháng, năm hiện tại
    DECLARE @currentYear NVARCHAR(2) = RIGHT(CONVERT(VARCHAR(4), YEAR(GETDATE())), 2);
    DECLARE @currentMonth NVARCHAR(2) = RIGHT('0' + CAST(MONTH(GETDATE()) AS NVARCHAR), 2);
    DECLARE @currentDay NVARCHAR(2) = RIGHT('0' + CAST(DAY(GETDATE()) AS NVARCHAR), 2);

    -- Lặp qua tất cả các dòng được chèn
    DECLARE cur CURSOR FOR 
    SELECT maKH, tenKH, SDT, diemThuong, gioiTinh FROM inserted;

    OPEN cur;
    FETCH NEXT FROM cur INTO @maKH, @tenKH, @SDT, @diemThuong, @gioiTinh;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        -- Kiểm tra điều kiện cho maKH
        IF NOT (
            @maKH LIKE 'KH[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'
			AND SUBSTRING(@maKH,3,2) = RIGHT(CONVERT(VARCHAR(4), YEAR(GETDATE())), 2)
			AND SUBSTRING(@maKH, 5, 2) = RIGHT('0' + CAST(MONTH(GETDATE()) AS NVARCHAR), 2)
			AND SUBSTRING(@maKH, 7, 2) = RIGHT('0' + CAST(DAY(GETDATE()) AS NVARCHAR), 2)
			AND ISNUMERIC(SUBSTRING(@maKH, 9, 5)) = 1
			AND LEN(SUBSTRING(@maKH, 9, 5)) = 5
        )
        BEGIN
            RAISERROR('Mã khách hàng không hợp lệ!', 16, 1);
            ROLLBACK TRANSACTION;
            RETURN;
        END
        
        -- Kiểm tra điều kiện cho tenKH
        IF NOT (
            LEN(@tenKH) BETWEEN 2 AND 50 AND @tenKH NOT LIKE '%[^a-zA-Z ]%'
        )
        BEGIN
            RAISERROR('Tên khách hàng không hợp lệ!', 16, 1);
            ROLLBACK TRANSACTION;
            RETURN;
        END

        -- Kiểm tra điều kiện cho SDT
        IF NOT (
            LEN(@SDT) = 10 AND @SDT NOT LIKE '%[^0-9]%' AND 
            NOT EXISTS (SELECT 1 FROM KhachHang WHERE SDT = @SDT)
        )
        BEGIN
            RAISERROR('Số điện thoại không hợp lệ hoặc đã tồn tại!', 16, 1);
            ROLLBACK TRANSACTION;
            RETURN;
        END

        -- Nếu tất cả các điều kiện đều hợp lệ, thực hiện thêm bản ghi vào bảng KhachHang
        INSERT INTO KhachHang (maKH, tenKH, SDT, diemThuong, gioiTinh)
        VALUES (@maKH, @tenKH, @SDT, @diemThuong, @gioiTinh);

        FETCH NEXT FROM cur INTO @maKH, @tenKH, @SDT, @diemThuong, @gioiTinh;
    END

    CLOSE cur;
    DEALLOCATE cur;
END;



--TẠO BẢNG LOẠI HÓA ĐƠN
CREATE TABLE LoaiHoaDon
(
	maLoaiHD NVARCHAR(20) NOT NULL PRIMARY KEY,
	tenLoaiHD NVARCHAR(50) NOT NULL
)
--Ràng buộc maLoaiHD
ALTER TABLE LoaiHoaDon
ADD CONSTRAINT chk_loaiHD CHECK (maLoaiHD IN ('BanThuoc', 'DoiThuoc', 'TraThuoc'));
--Ràng buộc tenLoaiHD
ALTER TABLE LoaiHoaDon
ADD CONSTRAINT chk_tenLoaiHD CHECK (
    LEN(tenLoaiHD) BETWEEN 2 AND 50  -- Độ dài từ 2 đến 50 ký tự
    AND tenLoaiHD NOT LIKE '%[^a-zA-Z ]%'  -- Chỉ chứa chữ cái và khoảng trắng
);

--Tạo bảng Hóa Đơn
CREATE TABLE HoaDon (
    maHD CHAR(13) NOT NULL PRIMARY KEY,   -- maHD is a string
    ngayLapHD DATE NOT NULL,                  -- ngayLapHD is a LocalDate (mapped to SQL DATE)
	tongTien DECIMAL(18,5) NOT NULL,                  -- tongTien is a double
    tienKhachTra DECIMAL(18,5) NOT NULL,             -- tienKhachTra is a double
	hinhThucThanhToan NVARCHAR(15) NOT NULL,
    trangThai BIT NOT NULL,               -- trangThai is a booleanư
	ghiChu Nvarchar(500)
);
--Thêm trường maKH vào bảng Hóa Đơn
ALTER TABLE HoaDon
ADD maKH Char(13) Not Null;

--Tạo khóa ngoại cho bảng HoaDon
ALTER TABLE HoaDon
ADD CONSTRAINT FK_KhachHang_HoaDon FOREIGN KEY (maKH) REFERENCES KhachHang(maKH)   -- Thiết lập khóa ngoại

--Thêm trường maNV vào bảng Hóa đơn
ALTER TABLE HoaDon
ADD maNV Char(10) Not Null;

--Tạo khóa ngoại cho bảng HoaDon
ALTER TABLE HoaDon
ADD CONSTRAINT FK_NhanVien_HoaDon FOREIGN KEY (maNV) REFERENCES NhanVien(maNV)   -- Thiết lập khóa ngoại

--Thêm trường maLoaiHD vào bảng Hóa đơn
ALTER TABLE HoaDon
ADD maLoaiHD Nvarchar(20) Not Null;

--Tạo khóa ngoại cho bảng HoaDon
ALTER TABLE HoaDon
ADD CONSTRAINT FK_LoaiHoaDon_HoaDon FOREIGN KEY (maLoaiHD) REFERENCES LoaiHoaDon(maLoaiHD);   -- Thiết lập khóa ngoại
--Tạo ràng buộc cho hinhThucThanhToan chỉ có 3 loại: TienMat,ChuyenKhoan,The
ALTER TABLE HoaDon
ADD CONSTRAINT chk_hinhThucThanhToan CHECK (hinhThucThanhToan IN ('TienMat', 'ChuyenKhoan', 'The'));

--Ràng buộc maHD
-- Thêm ràng buộc cho maHD
ALTER TABLE HoaDon
ADD CONSTRAINT chk_maHD CHECK (
    maHD LIKE 'HD[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'  -- Định dạng HDYYMMddRRRRR
    AND SUBSTRING(maHD, 3, 2) = RIGHT(CONVERT(VARCHAR(4), YEAR(GETDATE())), 2)  -- YY
    AND SUBSTRING(maHD, 5, 2) = RIGHT('0' + CAST(MONTH(GETDATE()) AS NVARCHAR), 2)  -- MM
    AND SUBSTRING(maHD, 7, 2) = RIGHT('0' + CAST(DAY(GETDATE()) AS NVARCHAR), 2)    -- dd
    AND ISNUMERIC(SUBSTRING(maHD, 9, 5)) = 1  -- RRRRR phải là số
    AND LEN(SUBSTRING(maHD, 9, 5)) = 5         -- Đảm bảo RRRRR có độ dài 5
);

--TRIGGER HÓA ĐƠN
-- TRIGGER HÓA ĐƠN
CREATE TRIGGER trg_CheckHoaDon
ON HoaDon
INSTEAD OF INSERT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @maHD CHAR(13),
            @maKH CHAR(13),
            @maNV CHAR(10),
            @maLoaiHD NVARCHAR(20),
            @ngayLapHD DATE,
            @tongTien DECIMAL(18,5),
            @tienKhachTra DECIMAL(18,5),
            @hinhThucThanhToan NVARCHAR(15),
            @trangThai BIT,
			@ghiChu NVARCHAR(500);

    -- Lặp qua tất cả các dòng được chèn
    DECLARE cur CURSOR FOR 
    SELECT maHD, maKH, maNV, maLoaiHD, ngayLapHD, tongTien, tienKhachTra, hinhThucThanhToan, trangThai ,ghiChu
    FROM inserted;

    OPEN cur;
    FETCH NEXT FROM cur INTO @maHD, @maKH, @maNV, @maLoaiHD, @ngayLapHD, @tongTien, @tienKhachTra, @hinhThucThanhToan, @trangThai,@ghiChu;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        -- Kiểm tra các điều kiện
        IF NOT (
            -- Kiểm tra ràng buộc cho maHD
            @maHD LIKE 'HD[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]' AND
            SUBSTRING(@maHD, 3, 2) = RIGHT(CONVERT(VARCHAR(4), YEAR(GETDATE())), 2) AND
            SUBSTRING(@maHD, 5, 2) = RIGHT('0' + CAST(MONTH(GETDATE()) AS NVARCHAR), 2) AND
            SUBSTRING(@maHD, 7, 2) = RIGHT('0' + CAST(DAY(GETDATE()) AS NVARCHAR), 2) AND
            ISNUMERIC(SUBSTRING(@maHD, 9, 5)) = 1 AND
            LEN(SUBSTRING(@maHD, 9, 5)) = 5 AND
            -- Kiểm tra tính duy nhất của maKH
            EXISTS (SELECT 1 FROM KhachHang WHERE maKH = @maKH) AND
            -- Kiểm tra tính hợp lệ cho maNV
            EXISTS (SELECT 1 FROM NhanVien WHERE maNV = @maNV) AND
            -- Kiểm tra loại hóa đơn
            EXISTS (SELECT 1 FROM LoaiHoaDon WHERE maLoaiHD = @maLoaiHD) AND
            -- Kiểm tra ngày lập hóa đơn không được là tương lai
            @ngayLapHD <= GETDATE()
        )
        BEGIN
            -- Nếu không thỏa mãn điều kiện, thực hiện rollback
            RAISERROR('Dữ liệu không hợp lệ. Không thể thêm hóa đơn.', 16, 1);
            ROLLBACK TRANSACTION;
            RETURN;
        END
        
        -- Nếu thỏa mãn, thực hiện thêm vào bảng
        INSERT INTO HoaDon (maHD, maKH, maNV, maLoaiHD, ngayLapHD, tongTien, tienKhachTra, hinhThucThanhToan, trangThai,ghiChu)
        VALUES (@maHD, @maKH, @maNV, @maLoaiHD, @ngayLapHD, @tongTien, @tienKhachTra, @hinhThucThanhToan, @trangThai,@ghiChu);

        FETCH NEXT FROM cur INTO @maHD, @maKH, @maNV, @maLoaiHD, @ngayLapHD, @tongTien, @tienKhachTra, @hinhThucThanhToan, @trangThai,@ghiChu;
    END

    CLOSE cur;
    DEALLOCATE cur;
END;


--Tạo bảng Loại Sản Phẩm
CREATE TABLE LoaiSanPham
(
	maLoaiSP VARCHAR(10) NOT NULL PRIMARY KEY,
	tenLoaiSP NVARCHAR(50)
)
-- Ràng buộc cho maLoaiSP (chỉ nhận 3 giá trị TH, TPCN, TBYT)
ALTER TABLE LoaiSanPham
ADD CONSTRAINT chk_maLoaiSP CHECK (maLoaiSP IN ('Thuoc', 'TPCN', 'TBYT'));

-- Ràng buộc cho tenLoaiSP (không quá 50 ký tự)
ALTER TABLE LoaiSanPham
ADD CONSTRAINT chk_tenLoaiSP CHECK (LEN(tenLoaiSP) <= 50);
--TẠO bảng SanPham
CREATE TABLE SanPham (
    maSP CHAR(13) NOT NULL PRIMARY KEY,  -- Mã sản phẩm
    tenSP NVARCHAR(50) NOT NULL,            -- Tên sản phẩm
	soLuong int Not Null,					-- số lượng
    ngaySanXuat DATE NOT NULL,                       -- Ngày sản xuất
    ngayHetHan DATE NOT NULL,                        -- Ngày hết hạn
	khoiLuong Decimal (15,5) NOT NULL,					-- Khối Lượng
	donViTinh NVarchar(6) NOT NULL,						-- đơn Vị tính
    nhaCungCap NVARCHAR(100) NOT NULL,                -- Nhà cung cấp
    gia DECIMAL(18, 5) CHECK (gia > 0) NOT NULL,     -- Giá, phải lớn hơn 0
    congDung NVARCHAR(255) NOT NULL,                  -- Công dụng
    hinhAnhSP NVARCHAR(255) NOT NULL                  -- Đường dẫn hoặc URL của hình ảnh sản phẩm
);

--THÊM MÃ LOẠI SP VÀO BẢNG SẢN PHẨM
ALTER TABLE SanPham
ADD maLoaiSP VARCHAR(10) NOT NULL
--RÀNG BUỘC KHÓA NGOẠI CHO BẢNG SẢN PHẨM
ALTER TABLE SanPham
ADD CONSTRAINT FK_LoaiSanPham_SanPham FOREIGN KEY (maLoaiSP) REFERENCES LoaiSanPham(maLoaiSP)   -- Thiết lập khóa ngoại
--Ràng buộc số lượng
ALTER TABLE SanPham
ADD CONSTRAINT chk_soLuong CHECK (soLuong > 0);
--Ràng buộc khoiLuong
ALTER TABLE SanPham
ADD CONSTRAINT chk_khoiLuong CHECK (khoiLuong > 0);
--Ràng Buộc đơn vị tính
ALTER TABLE SanPham
ADD CONSTRAINT chk_donViTinh CHECK (donViTinh IN (N'Vỉ', N'Chai', N'Viên', N'Hộp'));
--RÀNG BUỘC MASP
ALTER TABLE SanPham
ADD CONSTRAINT chk_maSP CHECK (
        maSP LIKE 'SP[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'  -- Định dạng SPYYMMDDRRRRR
        AND SUBSTRING(maSP, 3, 2) = RIGHT(CONVERT(VARCHAR(4), YEAR(GETDATE())), 2)  -- YY
        AND SUBSTRING(maSP, 5, 2) = RIGHT('0' + CAST(MONTH(GETDATE()) AS NVARCHAR), 2)  -- MM
        AND SUBSTRING(maSP, 7, 2) = RIGHT('0' + CAST(DAY(GETDATE()) AS NVARCHAR), 2)    -- DD
        AND ISNUMERIC(SUBSTRING(maSP, 9, 5)) = 1  -- RRRRR phải là số
        AND LEN(SUBSTRING(maSP, 9, 5)) = 5         -- Đảm bảo RRRRR có độ dài 5
    )

--Ràng buộc tên sp
ALTER TABLE SanPham
ADD CONSTRAINT chk_tenSP CHECK (LEN(tenSP) <= 50);
-- Ràng buộc cho ngaySanXuat phải nhỏ hơn hoặc bằng ngày hiện tại
ALTER TABLE SanPham
ADD CONSTRAINT chk_ngaySanXuat CHECK (ngaySanXuat <= GETDATE());

-- Ràng buộc cho ngayHetHan phải lớn hơn hoặc bằng ngaySanXuat
ALTER TABLE SanPham
ADD CONSTRAINT chk_ngayHetHan CHECK (ngayHetHan >= ngaySanXuat);

-- Ràng buộc cho nhaCungCap không được quá 100 ký tự
ALTER TABLE SanPham
ADD CONSTRAINT chk_nhaCungCap CHECK (LEN(nhaCungCap) <= 100);

-- Ràng buộc cho gia phải lớn hơn hoặc bằng 0
ALTER TABLE SanPham
ADD CONSTRAINT chk_gia CHECK (gia >= 0);

-- Ràng buộc cho congDung không được quá 255 ký tự
ALTER TABLE SanPham
ADD CONSTRAINT chk_congDung CHECK (LEN(congDung) <= 255);

-- Ràng buộc cho hinhAnhSP không được quá 255 ký tự
ALTER TABLE SanPham
ADD CONSTRAINT chk_hinhAnhSP CHECK (LEN(hinhAnhSP) <= 255);

--TRigger bảng Sản phẩm
CREATE TRIGGER trg_CheckSanPham
ON SanPham
INSTEAD OF INSERT
AS
BEGIN
    -- Kiểm tra điều kiện
    IF EXISTS (
        SELECT *
        FROM inserted
        WHERE 
            ngaySanXuat > GETDATE() OR
            ngayHetHan < ngaySanXuat OR
            LEN(nhaCungCap) > 100 OR
            gia < 0 OR
            LEN(congDung) > 255 OR
            LEN(hinhAnhSP) > 255 OR
            LEN(tenSP) > 50 OR
            maSP NOT LIKE 'SP[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]' OR
            SUBSTRING(maSP, 3, 2) != RIGHT(CONVERT(VARCHAR(4), YEAR(GETDATE())), 2) OR
            SUBSTRING(maSP, 5, 2) != RIGHT('0' + CAST(MONTH(GETDATE()) AS NVARCHAR), 2) OR
            SUBSTRING(maSP, 7, 2) != RIGHT('0' + CAST(DAY(GETDATE()) AS NVARCHAR), 2) OR
            ISNUMERIC(SUBSTRING(maSP, 9, 5)) = 0 OR
            LEN(SUBSTRING(maSP, 9, 5)) != 5
    )
    BEGIN
        RAISERROR ('Không thể thêm sản phẩm do không đủ điều kiện.', 16, 1);
        ROLLBACK TRANSACTION;  -- Thực hiện rollback
    END
    ELSE
    BEGIN
        -- Nếu đủ điều kiện, thêm bản ghi vào bảng
        INSERT INTO SanPham (maSP, tenSP,soLuong, ngaySanXuat, ngayHetHan,khoiLuong,donViTinh, nhaCungCap, gia, congDung, hinhAnhSP, maLoaiSP)
        SELECT maSP, tenSP,soLuong, ngaySanXuat, ngayHetHan,khoiLuong,donViTinh, nhaCungCap, gia, congDung, hinhAnhSP, maLoaiSP
        FROM inserted;
    END
END;


-- Tạo bảng ChiTietHoaDon
CREATE TABLE ChiTietHoaDon (
    maCTHD CHAR(15) NOT NULL PRIMARY KEY,  -- maCTHD format: CTHDYYMMddRRRRR
    soLuongSanPham INT CHECK (soLuongSanPham > 0), -- Số lượng sản phẩm phải lớn hơn 0
    tongTien DECIMAL(18, 5) CHECK (tongTien > 0)  -- Tổng tiền phải lớn hơn 0
);

-- Thêm maHD vào bảng ChiTietHoaDon
ALTER TABLE ChiTietHoaDon
ADD maHD CHAR(13) NOT NULL;

-- Ràng buộc khóa ngoại maHD cho bảng ChiTietHoaDon
ALTER TABLE ChiTietHoaDon
ADD CONSTRAINT FK_HoaDon_CTHD FOREIGN KEY (maHD) REFERENCES HoaDon(maHD);  -- Thiết lập khóa ngoại

-- Thêm maSP vào bảng ChiTietHoaDon
ALTER TABLE ChiTietHoaDon
ADD maSP CHAR(13) NOT NULL;

-- Ràng buộc khóa ngoại maSP cho bảng ChiTietHoaDon
ALTER TABLE ChiTietHoaDon
ADD CONSTRAINT FK_SP_CTHD FOREIGN KEY (maSP) REFERENCES SanPham(maSP);  -- Thiết lập khóa ngoại
-- Tạo trigger cho bảng ChiTietHoaDon
CREATE TRIGGER trg_CheckChiTietHoaDon
ON ChiTietHoaDon
INSTEAD OF INSERT
AS
BEGIN
    -- Kiểm tra điều kiện
    IF NOT EXISTS (
        SELECT 1
        FROM inserted
        WHERE 
            maCTHD LIKE 'CTHD[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]' AND  -- Định dạng đúng
            SUBSTRING(maCTHD, 5, 2) = RIGHT(CONVERT(VARCHAR(4), YEAR(GETDATE())), 2) AND  -- YY đúng
            SUBSTRING(maCTHD, 7, 2) = RIGHT('0' + CAST(MONTH(GETDATE()) AS NVARCHAR), 2) AND  -- MM đúng
            SUBSTRING(maCTHD, 9, 2) = RIGHT('0' + CAST(DAY(GETDATE()) AS NVARCHAR), 2) AND  -- DD đúng
            soLuongSanPham > 0 AND  -- Số lượng sản phẩm hợp lệ
            tongTien > 0  -- Tổng tiền hợp lệ
    )
    BEGIN
        -- Nếu có bản ghi không thỏa mãn điều kiện, thực hiện rollback
        RAISERROR('Không thể thêm bản ghi vào ChiTietHoaDon. Kiểm tra lại các điều kiện.', 16, 1);
        ROLLBACK TRANSACTION;
    END
    ELSE
    BEGIN
        -- Nếu đủ điều kiện, thêm bản ghi vào bảng
        INSERT INTO ChiTietHoaDon (maCTHD, soLuongSanPham, tongTien, maHD, maSP)
        SELECT maCTHD, soLuongSanPham, tongTien, maHD, maSP
        FROM inserted;
    END
END;

-- Thêm dữ liệu vào bảng TaiKhoan
INSERT INTO TaiKhoan (tenDangNhap, matKhau, phanQuyen, trangThai) VALUES
('adminUser', 'Admin@2024', 1, 1),
('staffUser', 'Staff2024#', 0, 1);


-- Thêm dữ liệu vào bảng LoaiNhanVien
INSERT INTO LoaiNhanVien (maLoaiNV, tenLoaiNV) VALUES
('NV', 'Nhân viên'),
('QL', 'Quản lý');

-- Thêm dữ liệu vào bảng NhanVien
INSERT INTO NhanVien ([maNV],[hoNV],[tenNV],[gioiTinh],[sdt],[cccd],[chucVu],[diaChi],[ngaySinh],[trangThai],[ngayVaoLam],[hinhAnhNV],[tenDangNhap],[maLoaiNV])
VALUES ('NV24011234', 'Nguyễn Văn', 'Anh','Nam', '0312345678', '012345678924', 'NhanVien', 'TP.HCM', '1999-05-09', 0, '2024-01-03', 'LêVănĐạt.jpg', 'adminUser', 'NV');
-- Them du lieu vao bang KhachHang
INSERT INTO KhachHang (maKH, tenKH, SDT, diemThuong, gioiTinh)
VALUES 
('KH24101912345', 'NguyenLeNhuAn', '0123456788', 10, 'Nữ');

-- Them du lieu vao bang LoaiHoaDon
INSERT INTO LoaiHoaDon (maLoaiHD, tenLoaiHD)
VALUES ('BanThuoc', 'BanThuoc'), 
       ('DoiThuoc', 'DoiThupc'), 
       ('TraThuoc', 'TraThuoc');
-- Them du lieu vao bang LoaiSanPham
INSERT INTO LoaiSanPham (maLoaiSP, tenLoaiSP)
VALUES 
('Thuoc', 'Thuốc'),
('TPCN', 'Thực phẩm chức năng'),
('TBYT', 'Thiết bị y tế');

-- Them du lieu vao bang HoaDon
-- Thêm dữ liệu mẫu vào bảng HoaDon
INSERT INTO HoaDon (maHD, ngayLapHD, tongTien, tienKhachTra, hinhThucThanhToan, trangThai, maKH, maNV, maLoaiHD)
VALUES 
    ('HD24101912345', '2024-09-30', 100000.00, 100000.00, 'TienMat', 1, 'KH24101912345', 'NV24011234', 'BanThuoc')

-- Them du lieu vao bang SanPham
INSERT INTO SanPham (maSP, tenSP,soLuong, ngaySanXuat, ngayHetHan,khoiLuong,donViTinh, nhaCungCap, gia, congDung, hinhAnhSP, maLoaiSP)
VALUES 
('SP24101900001', 'Vitamin C',500, '2024-09-30', '2025-09-30',200,'Chai', 'Công ty TNHH Dược Phẩm ABC', 150000.00000, 'Hỗ trợ tăng cường hệ miễn dịch', 'http://example.com/images/vitamin_c.jpg', 'TPCN')
-- Them du lieu vao bang ChiTietHoaDon
INSERT INTO ChiTietHoaDon (maCTHD, soLuongSanPham, tongTien, maHD, maSP)
VALUES 
('CTHD24101900001', 3, 100000.00, 'HD24101912345', 'SP24101900001')

