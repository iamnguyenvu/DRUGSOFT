Create Database DRUGSOFT
USE DRUGSOFT

USE master;
ALTER DATABASE [DRUGSOFT] SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
DROP DATABASE [DRUGSOFT];


Create Table TaiKhoan
(
	tenDangNhap Char(10) not null Primary Key,        ---Tên đăng nhập là khóa chính và <=6 tenDangNhap <= 16 ký tự
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
	hotenNV Nvarchar(30) Not Null,
	gioiTinh Nvarchar (5) Not Null,   -- Giới Tính
    sdt Nvarchar(10) Not Null,       -- Số điện thoại
    cccd Char(12) Not Null,      -- Căn cước công dân
    diaChi Nvarchar(255) Not null,           -- Địa chỉ
    ngaySinh Date NOT NULL,         -- Ngày sinh (LocalDate tương ứng với kiểu DATE)
    trangThai BIT NOT NULL,          -- Trạng thái (0: Đã nghỉ việc, 1: Đang làm)
	ngayVaoLam Date Not Null,
	hinhAnhNV NVARCHAR(255) NOT NULL
);
ALTER TABLE NhanVien
ADD email NVARCHAR(50);
UPDATE NhanVien
SET email = hotenNV + '@gmail.com'
--set Khóa ngoại cho bảng TaiKhoan
ALTER TABLE TaiKhoan
ADD CONSTRAINT FK_NhanVien_TaiKhoan
FOREIGN KEY (tenDangNhap) REFERENCES NhanVien(maNV);
--Ràng buộc giới tính
ALTER TABLE NhanVien
ADD CONSTRAINT chk_gioiTinh CHECK (gioiTinh IN (N'Nam', N'Nữ'))


-- ràng buộc sdt
ALTER TABLE NhanVien
ADD CONSTRAINT chk_sdt CHECK (
    SDT IS NOT NULL AND                           -- Không được để trống
    LEN(SDT) = 10 AND                             -- Độ dài phải là 10 ký tự
    SDT NOT LIKE '%[^0-9]%' AND                   -- Chỉ chứa ký tự số
    (SDT LIKE '03%' OR SDT LIKE '05%' OR         -- Bắt đầu bằng 03, 05, 07, hoặc 09
     SDT LIKE '07%' OR SDT LIKE '08%' OR SDT LIKE '09%')
);

--Ràng buộc cccd
ALTER TABLE NhanVien
ADD CONSTRAINT chk_cccd CHECK (cccd NOT LIKE '%[^0-9]%');


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
    INSERT INTO NhanVien (maNV, hotenNV,gioiTinh, sdt, cccd, diaChi, ngaySinh, trangThai, ngayVaoLam, hinhAnhNV, maLoaiNV)
    SELECT maNV, hotenNV,gioiTinh, sdt, cccd, diaChi, ngaySinh, trangThai, ngayVaoLam, hinhAnhNV, maLoaiNV
    FROM inserted;
END;

--Tạo bảng Khách Hàng
CREATE TABLE KhachHang (
    sdtKH CHAR(10) NOT NULL primary key,           -- Số điện thoại (String)
    tenKH NVARCHAR(50) NOT NULL,         -- Tên khách hàng (String)
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
    DECLARE @sdtKH CHAR(10), @tenKH NVARCHAR(50), @diemThuong INT, @gioiTinh NVARCHAR(10);
    
    -- Lặp qua tất cả các dòng được chèn
    DECLARE cur CURSOR FOR 
    SELECT sdtKH, tenKH, diemThuong, gioiTinh FROM inserted;

    OPEN cur;
    FETCH NEXT FROM cur INTO @sdtKH, @tenKH, @diemThuong, @gioiTinh;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        -- Kiểm tra điều kiện cho sdtKH
        IF (
            @sdtKH IS NULL OR                                 -- Không được để trống
            LEN(@sdtKH) <> 10 OR                             -- Độ dài phải là 10 ký tự
            @sdtKH LIKE '%[^0-9]%' OR                        -- Chỉ chứa ký tự số
            NOT (@sdtKH LIKE '03%' OR @sdtKH LIKE '05%' OR   -- Bắt đầu bằng 03, 05, 07, hoặc 09
                 @sdtKH LIKE '07%' OR @sdtKH LIKE '08%' OR @sdtKH LIKE '09%')
        )
        BEGIN
            RAISERROR('Số Điện Thoại khách hàng không hợp lệ!', 16, 1);
            ROLLBACK TRANSACTION;
            RETURN;
        END
        
        -- Kiểm tra điều kiện cho tenKH
        IF (
            LEN(@tenKH) < 2 OR LEN(@tenKH) > 50 OR           -- Độ dài tên phải từ 2 đến 50 ký tự
            @tenKH LIKE '%[^a-zA-Z ]%'                        -- Chỉ chứa ký tự chữ cái và khoảng trắng
        )
        BEGIN
            RAISERROR('Tên khách hàng không hợp lệ!', 16, 1);
            ROLLBACK TRANSACTION;
            RETURN;
        END

        -- Nếu tất cả các điều kiện đều hợp lệ, thực hiện thêm bản ghi vào bảng KhachHang
        INSERT INTO KhachHang (sdtKH, tenKH, diemThuong, gioiTinh)
        VALUES (@sdtKH, @tenKH, @diemThuong, @gioiTinh);

        FETCH NEXT FROM cur INTO @sdtKH, @tenKH, @diemThuong, @gioiTinh;
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
ADD CONSTRAINT chk_loaiHD CHECK (maLoaiHD IN ('BanSanPham', 'DoiSanPham', 'TraSanPham'));
--Ràng buộc tenLoaiHD
ALTER TABLE LoaiHoaDon
ADD CONSTRAINT chk_tenLoaiHD CHECK (
    LEN(tenLoaiHD) BETWEEN 2 AND 50  -- Độ dài từ 2 đến 50 ký tự
    AND tenLoaiHD NOT LIKE '%[^a-zA-Z ]%'  -- Chỉ chứa chữ cái và khoảng trắng
);

--Tạo bảng Hóa Đơn
CREATE TABLE HoaDon (
    maHD CHAR(13) NOT NULL PRIMARY KEY,   -- maHD is a string
    ngayLapHD DATETIME NOT NULL,                  -- ngayLapHD is a LocalDate (mapped to SQL DATE)
	tongTien DECIMAL(18,5) NOT NULL,                  -- tongTien is a double
	tienGiam DECIMAL(18,5) NOT NULL,
	hinhThucThanhToan NVARCHAR(15) NOT NULL,
    trangThai BIT NOT NULL,               -- trangThai is a booleanư
	ghiChu NVARCHAR(100)
);
--Thêm trường maKH vào bảng Hóa Đơn
ALTER TABLE HoaDon
ADD sdtKH Char(10);

--Tạo khóa ngoại cho bảng HoaDon
ALTER TABLE HoaDon
ADD CONSTRAINT FK_KhachHang_HoaDon FOREIGN KEY (sdtKH) REFERENCES KhachHang(sdtKH)   -- Thiết lập khóa ngoại

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

-- TRIGGER HÓA ĐƠN
CREATE TRIGGER trg_CheckHoaDon
ON HoaDon
INSTEAD OF INSERT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @maHD CHAR(13),
            @sdtKH CHAR(10),
            @maNV CHAR(10),
            @maLoaiHD NVARCHAR(20),
            @ngayLapHD DATE,
            @tongTien DECIMAL(18,5),
			@tienGiam DECIMAL(18,5),
            @hinhThucThanhToan NVARCHAR(15),
            @trangThai BIT,
			@ghiChu NVARCHAR(100);

    -- Lặp qua tất cả các dòng được chèn
    DECLARE cur CURSOR FOR 
    SELECT maHD, sdtKH, maNV, maLoaiHD, ngayLapHD, tongTien,tienGiam, hinhThucThanhToan, trangThai ,ghiChu
    FROM inserted;

    OPEN cur;
    FETCH NEXT FROM cur INTO @maHD, @sdtKH, @maNV, @maLoaiHD, @ngayLapHD, @tongTien,@tienGiam, @hinhThucThanhToan, @trangThai,@ghiChu;

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
            EXISTS (SELECT 1 FROM KhachHang WHERE sdtKH = @sdtKH) AND
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
        INSERT INTO HoaDon (maHD, sdtKH, maNV, maLoaiHD, ngayLapHD, tongTien,tienGiam, hinhThucThanhToan, trangThai,ghiChu)
        VALUES (@maHD, @sdtKH, @maNV, @maLoaiHD, @ngayLapHD, @tongTien,@tienGiam, @hinhThucThanhToan, @trangThai,@ghiChu);

        FETCH NEXT FROM cur INTO @maHD, @sdtKH, @maNV, @maLoaiHD, @ngayLapHD, @tongTien,@tienGiam, @hinhThucThanhToan, @trangThai,@ghiChu;
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
	thanhPhan NVARCHAR(255) NOT NULL,				--Thành Phần 
    congDung NVARCHAR(255) NOT NULL,                  -- Công dụng
    hinhAnhSP NVARCHAR(255) NOT NULL,                  -- Đường dẫn hoặc URL của hình ảnh sản phẩm
	thue DECIMAL(18, 5)
);

--THÊM MÃ LOẠI SP VÀO BẢNG SẢN PHẨM
ALTER TABLE SanPham
ADD maLoaiSP VARCHAR(10) NOT NULL
--RÀNG BUỘC KHÓA NGOẠI CHO BẢNG SẢN PHẨM
ALTER TABLE SanPham
ADD CONSTRAINT FK_LoaiSanPham_SanPham FOREIGN KEY (maLoaiSP) REFERENCES LoaiSanPham(maLoaiSP)   -- Thiết lập khóa ngoại
--Ràng buộc số lượng
ALTER TABLE SanPham
ADD CONSTRAINT chk_soLuong CHECK (soLuong >= 0);
--Ràng buộc khoiLuong
ALTER TABLE SanPham
ADD CONSTRAINT chk_khoiLuong CHECK (khoiLuong > 0);
--Ràng Buộc đơn vị tính

ALTER TABLE SanPham
ADD CONSTRAINT chk_donViTinh CHECK (donViTinh IN (N'Vỉ', N'Chai', N'Viên', N'Hộp',N'Cái'));

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
        INSERT INTO SanPham (maSP, tenSP,soLuong, ngaySanXuat, ngayHetHan,khoiLuong,donViTinh, nhaCungCap, gia,thanhPhan, congDung, hinhAnhSP, maLoaiSP,thue)
        SELECT maSP, tenSP,soLuong, ngaySanXuat, ngayHetHan,khoiLuong,donViTinh, nhaCungCap, gia,thanhPhan, congDung, hinhAnhSP, maLoaiSP,thue
        FROM inserted;
    END
END;


-- Tạo bảng ChiTietHoaDon
CREATE TABLE ChiTietHoaDon (
    soLuongSanPham INT CHECK (soLuongSanPham > 0), -- Số lượng sản phẩm phải lớn hơn 0
    thanhTien DECIMAL(18, 5) CHECK (thanhTien > 0)  -- Tổng tiền phải lớn hơn 0
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

ALTER TABLE ChiTietHoaDon
ADD CONSTRAINT PK_TCTHD PRIMARY KEY (maHD, maSP);


-- Thêm dữ liệu vào bảng LoaiNhanVien
INSERT INTO LoaiNhanVien (maLoaiNV, tenLoaiNV) VALUES
('NV', 'Nhân viên'),
('QL', 'Quản lý');

-- Thêm dữ liệu vào bảng NhanVien
INSERT INTO NhanVien ([maNV],[hotenNV],[gioiTinh],[sdt],[cccd],[diaChi],[ngaySinh],[trangThai],[ngayVaoLam],[hinhAnhNV],[maLoaiNV])
VALUES 
	('NV24011234', N'Nguyễn Văn Anh',N'Nam', '0312345678', '032345678924', 'TP.HCM', '1999-05-09', 0, '2024-01-03', N'/img/imgNhanVien/nam1.jpg', 'QL'),
	('NV24011367', N'Lê Anh Thư',N'Nữ', '0948976223', '055999720718', 'TP.HCM', '1997-09-17', 0, '2024-01-25', N'/img/imgNhanVien/nu1.jpg', 'NV'),
	('NV24021864', N'Đặng Lê An',N'Nam', '0319870627', '075376892083', 'TP.HCM', '1998-03-28', 0, '2024-02-10', N'/img/imgNhanVien/nam2.jpg', 'NV'),
	('NV24031897', N'Nguyễn Chí Dũng',N'Nam', '0776793848', '087383949842', 'TP.HCM', '1999-07-09', 0, '2024-03-05', N'/img/imgNhanVien/nam3.jpg', 'NV'),
	('NV24041765', N'Lê Hoài Thu',N'Nữ', '0987357828', '037906523688', 'TP.HCM', '2000-08-25', 0, '2024-04-10', N'/img/imgNhanVien/nu2.jpg', 'NV'),
	('NV24051823', N'Phạm Hoàng Huy',N'Nam', '0368236574', '043456789102', 'TP.HCM', '2001-01-15', 0, '2024-05-12', N'/img/imgNhanVien/nam4.jpg', 'NV'),
	('NV24061952', N'Trần Thanh Phong',N'Nữ', '0923456789', '094856789101', 'TP.HCM', '1997-10-23', 0, '2024-06-18', N'/img/imgNhanVien/nu3.jpg', 'NV'),
	('NV24071324', N'Vũ Minh Thư',N'Nam', '0303456789', '047456789105', 'TP.HCM', '1998-11-11', 0, '2024-07-20', N'/img/imgNhanVien/nam5.jpg', 'NV'),
	('NV24081476', N'Nguyễn Văn Toàn',N'Nữ', '0987456234', '093456728901', 'TP.HCM', '2002-04-05', 0, '2024-08-22', N'/img/imgNhanVien/nu4.jpg', 'NV'),
	('NV24091365', N'Đoàn Thị Minh',N'Nữ', '0982345671', '094376282901', 'TP.HCM', '1999-02-07', 0, '2024-09-30', N'/img/imgNhanVien/nu5.jpg', 'NV'),
	('NV24101987', N'Đỗ Quốc Anh',N'Nam', '0934678912', '041234578901', 'TP.HCM', '1995-07-25', 0, '2024-10-03', N'/img/imgNhanVien/nam6.jpg', 'NV'),
	('NV24112432', N'Trần Thị Mai',N'Nữ', '0987567891', '021578902345', 'TP.HCM', '2000-04-17', 0, '2024-10-01', N'/img/imgNhanVien/nu6.jpg', 'NV'),
	('NV24122567', N'Lê Văn Phúc',N'Nam', '0309782345', '068123457689', 'TP.HCM', '1999-06-10', 0, '2024-09-20', N'/img/imgNhanVien/nam7.jpg','NV');
-- Thêm dữ liệu vào bảng NhanVien
	INSERT INTO TaiKhoan (tenDangNhap, matKhau, phanQuyen, trangThai) VALUES
('NV24011234', 'Admin@2024', 1, 1),
('NV24011367', 'Staff2024#', 0, 1),
('NV24021864', 'Staff2024#', 0, 1),
('NV24031897', 'Staff2024#', 0, 1),
('NV24041765', 'Staff2024#', 0, 1),
('NV24051823', 'Staff2024#', 0, 1),
('NV24061952', 'Staff2024#', 0, 1),
('NV24071324', 'Staff2024#', 0, 1),
('NV24081476', 'Staff2024#', 0, 1),
('NV24091365', 'Staff2024#', 0, 1),
('NV24101987', 'Staff2024#', 0, 1),
('NV24112432', 'Staff2024#', 0, 1),
('NV24122567', 'Staff2024#', 0, 1);
-- Them du lieu vao bang KhachHang
INSERT INTO KhachHang (sdtKH, tenKH, diemThuong, gioiTinh)
VALUES 
('0912345678', N'Ngô Văn Hùng', 1200, 'Nam'),
('0387654320', N'Phạm Thị Lan', 1600, 'Nu'),
('0519876543', N'Trần Văn Sơn', 1900, 'Nam'),
('0734567890', N'Nguyễn Thị Mai', 2100, 'Nu'),
('0823456779', N'Hoàng Mạnh Tuấn', 2400, 'Nam'),
('0932345678', N'Lê Thị Linh', 1300, 'Nu'),
('0876543210', N'Nguyễn Mạnh Quốc', 1600, 'Nam'),
('0780654321', N'Hoàng Văn Thanh', 2000, 'Nam'),
('0523456789', N'Phạm Thị Mai', 2200, 'Nu'),
('0318765432', N'Lê Văn Bảo', 2500, 'Nam'),
('0302345678', N'Đỗ Văn Quang', 1300, 'Nam'),
('0535678901', N'Lê Thị Bảo Ngọc', 1900, 'Nu'),
('0745678901', N'Trần Văn Đạt', 1700, 'Nam'),
('0818765432', N'Nguyễn Khánh Linh', 2500, 'Nam'),
('0930045678', N'Ngô Thị Lan Ngọc', 1000, 'Nu'),
('0912987654', N'PhamThiHoa', 1800, 'Nu'),
('0802345678', N'PhamHoaiLinh', 2200, 'Nu'),
('0723456789', N'NguyenVanHung', 1900, 'Nam'),
('0509876543', N'Lê Thị Thảo', 2000, 'Nu'),
('0312987654', N'Lê Thị Kim', 1800, 'Nu'),
('0376543210', N'Đỗ Văn Lâm', 1400, 'Nam'),
('0787654321', N'Lê Mạnh Đạt', 1800, 'Nam'),
('0823456789', N'Ngô Minh Thu', 2100, 'Nu'),
('0312345678', N'Hoàng Văn Đức', 2300, 'Nam');



-- Them du lieu vao bang LoaiHoaDon
INSERT INTO LoaiHoaDon (maLoaiHD, tenLoaiHD)
VALUES ('BanSanPham', N'Bán Sản Phẩm'), 
       ('DoiSanPham', N'Đổi Sản Phẩm'), 
       ('TraSanPham', N'Trả Sản Phẩm');
-- Them du lieu vao bang LoaiSanPham
INSERT INTO LoaiSanPham (maLoaiSP, tenLoaiSP)
VALUES 
('Thuoc', N'Thuốc'),
('TPCN', N'Thực phẩm chức năng'),
('TBYT', N'Thiết bị y tế');

-- Thêm dữ liệu vào bảng HoaDon


INSERT INTO HoaDon (maHD, ngayLapHD, tongTien, tienGiam, hinhThucThanhToan, trangThai, sdtKH, maNV, maLoaiHD)
VALUES 
    -- Tháng 1

	('HD24110100004', '2024-11-01 09:30:00', 150000.00, 1500, N'Tiền Mặt', 1, null, 'NV24122567', 'BanSanPham')
INSERT INTO ChiTietHoaDon ( soLuongSanPham, thanhTien, maHD, maSP) 
VALUES


    ( 5, 15000.00, 'HD24110100004', 'SP23070100001'),
    ( 2, 20000.00, 'HD24110100004', 'SP23070200002')

INSERT INTO HoaDon (maHD, ngayLapHD, tongTien, tienGiam, hinhThucThanhToan, trangThai, sdtKH, maNV, maLoaiHD)
VALUES 
    -- Tháng 1

	('HD24011567890', '2024-01-15 09:30:00', 150000.00, 1500, N'Tiền Mặt', 1, '0912345678', 'NV24122567', 'BanSanPham'), -- khách hàng 1
    ('HD24010912345', '2024-01-09 10:00:00', 200000.00, 2000, N'Chuyển Khoản', 1, '0312987654', 'NV24122567', 'BanSanPham'), -- khách hàng 2
    ('HD24011154321', '2024-01-11 14:45:00', 175000.00, 1750, N'Thẻ Tín Dụng', 1, '0312987654', 'NV24122567', 'BanSanPham'), -- khách hàng 3
    ('HD24010867890', '2024-01-08 11:15:00', 100000.00, 1000, N'Tiền Mặt', 1, '0312987654', 'NV24122567', 'BanSanPham'), -- khách hàng 4
    ('HD24011598765', '2024-01-15 16:30:00', 300000.00, 3000, N'Tiền Mặt', 1, '0312987654', 'NV24122567', 'BanSanPham'), -- khách hàng 5

	('HD24022312345', '2024-02-23 10:30:00', 250000.00, 2500, N'Tiền Mặt', 1, '0745678901', 'NV24101987', 'BanSanPham'), -- khách hàng 6
    ('HD24021767890', '2024-02-17 12:00:00', 320000.00, 3200, N'Chuyển Khoản', 1, '0745678901', 'NV24101987', 'BanSanPham'), -- khách hàng 7
    ('HD24021198765', '2024-02-11 09:45:00', 110000.00, 1100, N'Thẻ Tín Dụng', 1, '0745678901', 'NV24101987', 'BanSanPham'), -- khách hàng 8
    ('HD24020554321', '2024-02-05 14:15:00', 175000.00, 1750, N'Tiền Mặt', 1, '0745678901', 'NV24101987', 'BanSanPham'), -- khách hàng 9
    ('HD24022887654', '2024-02-28 16:00:00', 500000.00, 5000, N'Tiền Mặt', 1, '0745678901', 'NV24101987', 'BanSanPham'),

    -- Tháng 3
	('HD24031412345', '2024-03-14 10:30:00', 180000.00, 1800, N'Tiền Mặt', 1, '0318765432', 'NV24101987', 'BanSanPham'), -- khách hàng 11
    ('HD24030967890', '2024-03-09 12:00:00', 240000.00, 2400, N'Thẻ Tín Dụng', 1, '0318765432', 'NV24101987', 'BanSanPham'), -- khách hàng 12
    ('HD24031154321', '2024-03-11 09:45:00', 125000.00, 1250, N'Tiền Mặt', 1, '0318765432', 'NV24101987', 'BanSanPham'), -- khách hàng 13
    ('HD24030898765', '2024-03-08 14:15:00', 190000.00, 1900, N'Chuyển Khoản', 1, '0318765432', 'NV24101987', 'BanSanPham'), -- khách hàng 14
    ('HD24031587654', '2024-03-15 16:00:00', 220000.00, 2200, N'Tiền Mặt', 1, '0318765432', 'NV24101987', 'BanSanPham'), -- khách hàng 15

    -- Tháng 4
    ('HD24041467890', '2024-04-14 10:45:00', 275000.00, 2750, N'Thẻ Tín Dụng', 1, '0912987654', 'NV24071324', 'BanSanPham'), -- khách hàng 16
    ('HD24040954321', '2024-04-09 09:30:00', 150000.00, 1500, N'Tiền Mặt', 1, '0912987654', 'NV24071324', 'BanSanPham'), -- khách hàng 17
    ('HD24041198765', '2024-04-11 14:20:00', 210000.00, 2100, N'Chuyển Khoản', 1, '0912987654', 'NV24071324', 'BanSanPham'), -- khách hàng 18
    ('HD24040812345', '2024-04-08 11:00:00', 185000.00, 1850, N'Tiền Mặt', 1, '0912987654', 'NV24071324', 'BanSanPham'), -- khách hàng 19
    ('HD24041565432', '2024-04-15 16:30:00', 300000.00, 3000, N'Tiền Mặt', 1, '0912987654', 'NV24071324', 'BanSanPham'), -- khách hàng 20

    -- Tháng 5
    ('HD24051498765', '2024-05-14 09:15:00', 210000.00, 2100, N'Tiền Mặt', 1, '0802345678', 'NV24101987', 'BanSanPham'), -- khách hàng 21
    ('HD24050912345', '2024-05-09 10:30:00', 170000.00, 1700, N'Chuyển Khoản', 1, '0802345678', 'NV24101987', 'BanSanPham'), -- khách hàng 22
    ('HD24051154321', '2024-05-11 14:45:00', 180000.00, 1800, N'Thẻ Tín Dụng', 1, '0802345678', 'NV24101987', 'BanSanPham'), -- khách hàng 23
    ('HD24050867890', '2024-05-08 11:00:00', 135000.00, 1350, N'Tiền Mặt', 1, '0802345678', 'NV24101987', 'BanSanPham'), -- khách hàng 24
    ('HD24051587654', '2024-05-15 16:20:00', 320000.00, 3200, N'Tiền Mặt', 1, '0802345678', 'NV24101987', 'BanSanPham'), -- khách hàng 25

    -- Tháng 6
    ('HD24061412345', '2024-06-14 10:30:00', 275000.00, 2750, N'Tiền Mặt', 1, '0318765432', 'NV24112432', 'BanSanPham'), -- khách hàng 26
    ('HD24060967890', '2024-06-09 15:00:00', 300000.00, 3000, N'Chuyển Khoản', 1, '0318765432', 'NV24112432', 'BanSanPham'), -- khách hàng 27
    ('HD24061154321', '2024-06-11 09:45:00', 150000.00, 1500, N'Thẻ Tín Dụng', 1, '0318765432', 'NV24112432', 'BanSanPham'), -- khách hàng 28
    ('HD24060867890', '2024-06-08 12:20:00', 120000.00, 1200, N'Tiền Mặt', 1, '0318765432', 'NV24112432', 'BanSanPham'), -- khách hàng 29
    ('HD24061598765', '2024-06-15 14:55:00', 400000.00, 4000, N'Tiền Mặt', 1, '0318765432', 'NV24112432', 'BanSanPham'), -- khách hàng 30

-- Tháng 7
    ('HD24072112345', '2024-07-21 10:15:00', 180000.00, 1800, N'Tiền Mặt', 1, '0932345678', 'NV24071324', 'BanSanPham'), -- khách hàng 6
    ('HD24071567890', '2024-07-15 13:30:00', 230000.00, 2300, N'Chuyển Khoản', 1, '0932345678', 'NV24071324', 'BanSanPham'), -- khách hàng 7
    ('HD24071154321', '2024-07-11 09:45:00', 120000.00, 1200, N'Thẻ Tín Dụng', 1, '0932345678', 'NV24071324', 'BanSanPham'), -- khách hàng 8
    ('HD24070867890', '2024-07-08 11:20:00', 210000.00, 2100, N'Tiền Mặt', 1, '0932345678', 'NV24071324', 'BanSanPham'), -- khách hàng 9
    ('HD24071587654', '2024-07-15 15:00:00', 330000.00, 3300, N'Tiền Mặt', 1, '0932345678', 'NV24071324', 'BanSanPham'), -- khách hàng 10

-- Tháng 8
    ('HD24082112345', '2024-08-21 10:30:00', 250000.00, 2500, N'Tiền Mặt', 1, '0387654320', 'NV24101987', 'BanSanPham'), -- khách hàng 11
    ('HD24081567890', '2024-08-15 14:15:00', 300000.00, 3000, N'Chuyển Khoản', 1, '0387654320', 'NV24101987', 'BanSanPham'), -- khách hàng 12
    ('HD24081154321', '2024-08-11 09:50:00', 175000.00, 1750, N'Thẻ Tín Dụng', 1, '0387654320', 'NV24101987', 'BanSanPham'), -- khách hàng 13
    ('HD24080867890', '2024-08-08 11:40:00', 190000.00, 1900, N'Tiền Mặt', 1, '0387654320', 'NV24101987', 'DoiSanPham'), -- khách hàng 14
    ('HD24081598765', '2024-08-15 16:25:00', 420000.00, 4200, N'Tiền Mặt', 1, '0387654320', 'NV24101987', 'DoiSanPham'), -- khách hàng 15

-- Tháng 9
    ('HD24092112345', '2024-09-21 10:15:00', 190000.00, 1900, N'Tiền Mặt', 1, '0823456779', 'NV24071324', 'DoiSanPham'), -- khách hàng 16
    ('HD24091567890', '2024-09-15 13:45:00', 210000.00, 2100, N'Chuyển Khoản', 1, '0823456779', 'NV24071324', 'DoiSanPham'), -- khách hàng 17
    ('HD24091154321', '2024-09-11 09:20:00', 200000.00, 2000, N'Thẻ Tín Dụng', 1, '0823456779', 'NV24071324', 'DoiSanPham'), -- khách hàng 18
    ('HD24090867890', '2024-09-08 11:00:00', 160000.00, 1600, N'Tiền Mặt', 1, '0823456779', 'NV24071324', 'DoiSanPham'), -- khách hàng 19
    ('HD24091598765', '2024-09-15 15:30:00', 370000.00, 3700, N'Tiền Mặt', 1, '0823456779', 'NV24071324', 'DoiSanPham'), -- khách hàng 20

-- Tháng 10
    ('HD24102112345', '2024-10-21 09:30:00', 300000.00, 3000, N'Tiền Mặt', 1, '0802345678', 'NV24112432', 'TraSanPham'), -- khách hàng 21
    ('HD24101567890', '2024-10-15 14:45:00', 410000.00, 4100, N'Chuyển Khoản', 1, '0802345678', 'NV24112432', 'TraSanPham'), -- khách hàng 22
    ('HD24101154321', '2024-10-11 11:15:00', 230000.00, 2300, N'Thẻ Tín Dụng', 1, '0802345678', 'NV24112432', 'TraSanPham'), -- khách hàng 23
    ('HD24100867890', '2024-10-08 10:00:00', 270000.00, 2700, N'Tiền Mặt', 1, '0802345678', 'NV24112432', 'TraSanPham'), -- khách hàng 24
    ('HD24101587654', '2024-10-15 16:30:00', 480000.00, 4800, N'Tiền Mặt', 1, '0802345678', 'NV24112432', 'TraSanPham'); -- khách hàng 25






-- Them du lieu vao bang SanPham

INSERT INTO SanPham (maSP, tenSP, ngaySanXuat, ngayHetHan, nhaCungCap, gia, thanhPhan, congDung, hinhAnhSP, maLoaiSP, soLuong, khoiLuong, donViTinh)
VALUES
('SP23070100001', N'Viên uống Omega-3', '2023-07-01', '2024-07-01', N'Công ty TNHH Dược Phẩm ABC', 15000.00, N'Omega-3, Vitamin E', N'Hỗ trợ sức khỏe tim mạch', '/img/imgSanPham/omega3.jpg', 'TPCN', 50, 200, N'Viên'),
('SP23070200002', N'Viên uống Biotin', '2023-07-15', '2024-07-15', N'Công ty TNHH Dược Phẩm A', 12000.00, N'Biotin, Vitamin B7', N'Tăng cường sức khỏe tóc và da', '/img/imgSanPham/biotin.jpg', 'TPCN', 30, 500, N'Viên'),
('SP23070300003', N'Viên uống Probiotics', '2023-07-20', '2024-07-20', N'Công ty TNHH Dược Phẩm B', 13000.00, N'Probiotics, Lactobacillus', N'Hỗ trợ tiêu hóa', '/img/imgSanPham/probiotic.jpg', 'TPCN', 60, 300, N'Viên'),
('SP23070400004', N'Viên uống Vitamin C', '2023-07-25', '2024-07-25', N'Công ty TNHH Dược Phẩm C', 14000.00, N'Vitamin C, Acid Ascorbic', N'Tăng cường hệ miễn dịch','/img/imgSanPham/vitaminc.jpg', 'TPCN', 40, 700, N'Viên'),
('SP23070500005', N'Viên uống Vitamin D', '2023-07-30', '2024-07-30', N'Công ty TNHH Dược Phẩm D', 15000.00, N'Vitamin D3', N'Hỗ trợ xương', '/img/imgSanPham/vitamind.jpg', 'TPCN', 25, 500, N'Viên'),

('SP23080100006', N'Viên uống Chitosan', '2023-08-01', '2024-08-01', N'Công ty TNHH Dược Phẩm E', 16000.00, N'Chitosan', N'Hỗ trợ giảm cân', '/img/imgSanPham/chitosan.jpg', 'TPCN', 30, 300, N'Viên'),
('SP23080200007', N'Viên uống Sữa ong chúa', '2023-08-05', '2024-08-05', N'Công ty TNHH Dược Phẩm F', 17000.00, N'Sữa ong chúa, Vitamin A', N'Tăng cường sức khỏe', '/img/imgSanPham/suaongchua.jpg', 'TPCN', 60, 500, N'Chai'),
('SP23080300008', N'Viên uống Dầu cá', '2023-08-10', '2024-08-10', N'Công ty TNHH Dược Phẩm G', 18000.00, N'Dầu cá, Omega-3', N'Hỗ trợ sức khỏe tim mạch', '/img/imgSanPham/dauca.jpg', 'TPCN', 40, 200, N'Viên'),
('SP23080400009', N'Viên uống Vitamin B Complex', '2023-08-15', '2024-08-15', N'Công ty TNHH Dược Phẩm H', 15000.00, N'Vitamin B1, B2, B6, B12', N'Hỗ trợ chuyển hóa năng lượng', '/img/imgSanPham/vitaminbcomplex.jpg', 'TPCN', 50, 700, N'Chai'),
('SP23080500010', N'Viên uống Glucosamine', '2023-08-20', '2024-08-20', N'Công ty TNHH Dược Phẩm I', 20000.00, N'Glucosamine', N'Hỗ trợ khớp', '/img/imgSanPham/glucosamine.jpg', 'TPCN', 35, 300, N'Viên'),

('SP23090100011', N'Viên uống Magie', '2023-09-01', '2024-09-01', N'Công ty TNHH Dược Phẩm J', 19000.00, N'Magiê', N'Hỗ trợ giảm căng thẳng','/img/imgSanPham/magie.jpg', 'TPCN', 50, 500, N'Hộp'),
('SP23090200012', N'Viên uống Kẽm', '2023-09-05', '2024-09-05', N'Công ty TNHH Dược Phẩm K', 21000.00, N'Kẽm', N'Hỗ trợ miễn dịch', '/img/imgSanPham/kem.jpg', 'TPCN', 40, 200, N'Chai'),
('SP23090300013', N'Viên uống Selen', '2023-09-10', '2024-09-10', N'Công ty TNHH Dược Phẩm L', 25000.00, N'Selen', N'Chống oxi hóa', '/img/imgSanPham/selen.jpg', 'TPCN', 60, 700, N'Viên'),
('SP23090400014', N'Viên uống Ashwagandha', '2023-09-15', '2024-09-15', N'Công ty TNHH Dược Phẩm M', 26000.00, N'Ashwagandha', N'Hỗ trợ giảm căng thẳng', '/img/imgSanPham/ash.jpg', 'TPCN', 30, 500, N'Viên'),
('SP23090500015', N'Viên uống Nhân sâm', '2023-09-20', '2024-09-20', N'Công ty TNHH Dược Phẩm N', 24000.00, N'Nhân sâm', N'Tăng cường sức khỏe', '/img/imgSanPham/nhansan.jpg', 'TPCN', 25, 300, N'Hộp'),

('SP23100100016', N'Viên uống L-carnitine', '2023-10-01', '2024-10-01', N'Công ty TNHH Dược Phẩm O', 22000.00, N'L-carnitine', N'Hỗ trợ giảm cân', '/img/imgSanPham/l-carnitine.jpg', 'TPCN', 60, 700, N'Chai'),
('SP23100200017', N'Viên uống Acid folic', '2023-10-05', '2024-10-05', N'Công ty TNHH Dược Phẩm P', 15000.00, N'Acid folic', N'Hỗ trợ thai kỳ', '/img/imgSanPham/axidfolic.jpg', 'TPCN', 50, 500, N'Viên'),
('SP23100300018', N'Viên uống Vitamin A', '2023-10-10', '2024-10-10', N'Công ty TNHH Dược Phẩm Q', 20000.00, N'Vitamin A', N'Hỗ trợ thị lực', '/img/imgSanPham/vitamina.jpg', 'TPCN', 35, 300, N'Hộp'),
('SP23100400019', N'Viên uống Beta-carotene', '2023-10-15', '2024-10-15', N'Công ty TNHH Dược Phẩm R', 19000.00, N'Beta-carotene', N'Hỗ trợ miễn dịch', '/img/imgSanPham/beta-carotene.jpg', 'TPCN', 40, 700, N'Chai');


INSERT INTO SanPham (maSP, tenSP, ngaySanXuat, ngayHetHan, nhaCungCap, gia, thanhPhan, congDung, hinhAnhSP, maLoaiSP, soLuong, khoiLuong, donViTinh)
VALUES
('SP23100500020', N'Máy đo huyết áp', '2023-10-20', '2025-10-20', N'Công ty TNHH Thiết Bị Y Tế A', 750000.00, N'Nhựa, Kim loại', N'Theo dõi huyết áp','/img/imgSanPham/maydohuyetap.jpg', 'TBYT', 100, 1000, N'Cái'),
('SP23100600021', N'Máy đo đường huyết', '2023-10-21', '2025-10-21', N'Công ty TNHH Thiết Bị Y Tế B', 500000.00, N'Nhựa, Kim loại', N'Theo dõi đường huyết', '/img/imgSanPham/maydoduonghuyet.jpg', 'TBYT', 150, 500, N'Cái'),
('SP23100700022', N'Đèn chiếu hồng ngoại', '2023-10-22', '2025-10-22', N'Công ty TNHH Thiết Bị Y Tế C', 300000.00, N'Nhựa, Thủy tinh', N'Điều trị đau nhức', '/img/imgSanPham/denchieuhongngoai.jpg', 'TBYT', 80, 2000, N'Cái'),
('SP23100800023', N'Thiết bị xông mũi', '2023-10-23', '2025-10-23', N'Công ty TNHH Thiết Bị Y Tế D', 600000.00, N'Nhựa, Kim loại', N'Hỗ trợ điều trị hô hấp', '/img/imgSanPham/thietbixongmui.jpg', 'TBYT', 90, 1000, N'Cái'),
('SP23100900024', N'Máy đo nhiệt độ', '2023-10-24', '2025-10-24', N'Công ty TNHH Thiết Bị Y Tế E', 250000.00, N'Nhựa, Kim loại', N'Theo dõi nhiệt độ cơ thể', '/img/imgSanPham/maydonhietdo.jpg', 'TBYT', 120, 300, N'Cái'),

('SP23101000025', N'Máy massage', '2023-10-25', '2025-10-25', N'Công ty TNHH Thiết Bị Y Tế F', 850000.00, N'Nhựa, Kim loại', N'Thư giãn và giảm đau', '/img/imgSanPham/maymassage.jpg', 'TBYT', 70, 1500, N'Cái'),
('SP23101100026', N'Máy xông khí dung', '2023-10-26', '2025-10-26', N'Công ty TNHH Thiết Bị Y Tế G', 400000.00, N'Nhựa, Kim loại', N'Hỗ trợ điều trị hô hấp', '/img/imgSanPham/mayxongkhidung.jpg', 'TBYT', 110, 900, N'Cái'),
('SP23101200027', N'Kính áp tròng', '2023-10-27', '2025-10-27', N'Công ty TNHH Thiết Bị Y Tế H', 200000.00, N'Nhựa', N'Hỗ trợ thị lực', '/img/imgSanPham/kinhaptrong.jpg', 'TBYT', 150, 50, N'Cái'),
('SP23101300028', N'Thiết bị đo SpO2', '2023-10-28', '2025-10-28', N'Công ty TNHH Thiết Bị Y Tế I', 350000.00, N'Nhựa, Kim loại', N'Theo dõi mức độ oxy trong máu', '/img/imgSanPham/thietbidospo2.jpg', 'TBYT', 100, 200, N'Cái'),
('SP23101400029', N'Máy tập phục hồi chức năng', '2023-10-29', '2025-10-29', N'Công ty TNHH Thiết Bị Y Tế J', 950000.00, N'Nhựa, Kim loại', N'Thúc đẩy phục hồi sức khỏe', '/img/imgSanPham/maytapphuchoichucnang.jpg', 'TBYT', 60, 2000, N'Cái');

INSERT INTO SanPham (maSP, tenSP, ngaySanXuat, ngayHetHan, nhaCungCap, gia, thanhPhan, congDung, hinhAnhSP, maLoaiSP, soLuong, khoiLuong, donViTinh)
VALUES
('SP23101500030', N'Paracetamol', '2023-10-30', '2025-10-30', N'Công ty TNHH Dược Phẩm A', 5000.00, N'Paracetamol', N'Giảm đau và hạ sốt', '/img/imgSanPham/paracetamol.jpg', 'Thuoc', 200, 100, N'Viên'),
('SP23101600031', N'Amoxicillin', '2023-11-01', '2025-11-01', N'Công ty TNHH Dược Phẩm B', 8000.00, N'Amoxicillin', N'Kháng sinh phổ rộng', '/img/imgSanPham/amoxicillin.jpg', 'Thuoc', 150, 300, N'Viên'),
('SP23101700032', N'Ciprofloxacin', '2023-11-02', '2025-11-02', N'Công ty TNHH Dược Phẩm C', 9000.00, N'Ciprofloxacin', N'Điều trị nhiễm khuẩn', '/img/imgSanPham/ciprofloxacin.jpg', 'Thuoc', 120, 250, N'Viên'),
('SP23101800033', N'Ibuprofen', '2023-11-03', '2025-11-03', N'Công ty TNHH Dược Phẩm D', 6000.00, N'Ibuprofen', N'Giảm đau và kháng viêm', '/img/imgSanPham/ibuprofen.jpg', 'Thuoc', 180, 150, N'Viên'),
('SP23101900034', N'Siphen', '2023-11-04', '2025-11-04', N'Công ty TNHH Dược Phẩm E', 7000.00, N'Siphen', N'Giảm đau đầu','/img/imgSanPham/ciphen.jpg', 'Thuoc', 170, 100, N'Viên'),

('SP23102000035', N'Acetaminophen', '2023-11-05', '2025-11-05', N'Công ty TNHH Dược Phẩm F', 5500.00, N'Acetaminophen', N'Hỗ trợ giảm đau', '/img/imgSanPham/acetaminophen.jpg', 'Thuoc', 160, 100, N'Viên'),
('SP23102100036', N'Cetirizine', '2023-11-06', '2025-11-06', N'Công ty TNHH Dược Phẩm G', 6000.00, N'Cetirizine', N'Triệu chứng dị ứng', '/img/imgSanPham/cetirizine.jpg', 'Thuoc', 140, 200, N'Viên'),
('SP23102200037', N'Dexamethasone', '2023-11-07', '2025-11-07', N'Công ty TNHH Dược Phẩm H', 12000.00, N'Dexamethasone', N'Kháng viêm', '/img/imgSanPham/dexamethasone.jpg', 'Thuoc', 100, 250, N'Viên'),
('SP23102300038', N'Loratadine', '2023-11-08', '2025-11-08', N'Công ty TNHH Dược Phẩm I', 7500.00, N'Loratadine', N'Triệu chứng dị ứng', '/img/imgSanPham/loratadine.jpg', 'Thuoc', 130, 200, N'Viên'),
('SP23102400039', N'Simvastatin', '2023-11-09', '2025-11-09', N'Công ty TNHH Dược Phẩm J', 14000.00, N'Simvastatin', N'Hỗ trợ kiểm soát cholesterol', '/img/imgSanPham/simvastatin.jpg', 'Thuoc', 110, 300, N'Viên');

INSERT INTO SanPham (maSP, tenSP, ngaySanXuat, ngayHetHan, nhaCungCap, gia, thanhPhan, congDung, hinhAnhSP, maLoaiSP, soLuong, khoiLuong, donViTinh)
VALUES
('SP24103100030', N'Thuốc Grandaxin', '2023-10-15', '2025-10-30', N'Công ty TNHH Dược Phẩm A', 5000.00, N'Grandaxin', N'Giảm đau và hạ sốt', '/img/imgSanPham/grandaxin.jpg', 'Thuoc', 0, 100, N'Viên'),
('SP24103100031', N'Thuốc Savi Olanzapine', '2023-07-15', '2025-01-01', N'Công ty TNHH Dược Phẩm b', 5000.00, N'Olanzapine', N'Giảm đau và hạ sốt', '/img/imgSanPham/savi-olanzapine.jpg', 'Thuoc', 0, 100, N'Viên'),
('SP24103100032', N'Thuốc Zopistad 7.5 Stella', '2023-09-15', '2025-10-01', N'Công ty TNHH Dược Phẩm Dat', 5000.00, N'Stella', N'Giảm đau và hạ sốt', '/img/imgSanPham/zopistad.jpg', 'Thuoc', 0, 100, N'Viên'),
('SP24103100033', N'Thuốc Zapnex-10 DaviPharm', '2023-10-15', '2025-05-03', N'Công ty TNHH Dược Phẩm HZ', 5000.00, N'DaviPharm', N'Giảm đau và hạ sốt', '/img/imgSanPham/zapnex.jpg', 'Thuoc', 0, 100, N'Viên'),
('SP24103100034', N'Thuốc Trileptal 300 Novartis', '2023-11-15', '2025-06-10', N'Công ty TNHH Dược Phẩm AA', 5000.00, N'Novartis', N'Giảm đau và hạ sốt', '/img/imgSanPham/novartis.jpg', 'Thuoc', 0, 100, N'Viên'),
('SP24103100035', N'Thuốc Methicowel 500 Akums', '2023-12-15', '2025-03-02', N'Công ty TNHH Dược Phẩm ĐAA', 5000.00, N'Methicowel', N'Giảm đau và hạ sốt', '/img/imgSanPham/methicowel.jpg', 'Thuoc', 0, 100, N'Viên');

-- Them du lieu vao bang ChiTietHoaDon
INSERT INTO ChiTietHoaDon ( soLuongSanPham, thanhTien, maHD, maSP) 
VALUES


-- Tháng 7 năm trước
    ( 1, 15000.00, 'HD24092112345', 'SP23070100001'),
    ( 2, 20000.00, 'HD24092112345', 'SP23070200002'),
    ( 3, 17500.00, 'HD24092112345', 'SP23070300003'),
    ( 4, 10000.00, 'HD24092112345', 'SP23070400004'),
    ( 5, 30000.00, 'HD24092112345', 'SP23070500005'),

    -- Tháng 8 năm trước
    ( 2, 15000.00, 'HD24030967890', 'SP23080100006'),
    ( 3, 20000.00, 'HD24030967890', 'SP23080200007'),
    ( 5, 17500.00, 'HD24030967890', 'SP23080300008'),
    ( 4, 10000.00, 'HD24030967890', 'SP23080400009'),
    ( 8, 30000.00, 'HD24030967890', 'SP23080500010')


	UPDATE SanPham
SET thue = CASE 
              WHEN maLoaiSP = 'Thuoc' OR maLoaiSP = 'TBYT' THEN 5
              WHEN maLoaiSP = 'TPCN' THEN 10
              ELSE NULL  -- Giá trị mặc định nếu không khớp với bất kỳ điều kiện nào
           END;


-- Sử dụng vòng lặp để thêm ngẫu nhiên 2 đến 4 chi tiết cho mỗi hóa đơn
DECLARE @maHD char(13), @count INT;

-- Duyệt qua từng hóa đơn
DECLARE invoiceCursor CURSOR FOR 
SELECT maHD FROM HoaDon;

OPEN invoiceCursor;
FETCH NEXT FROM invoiceCursor INTO @maHD;

WHILE @@FETCH_STATUS = 0
BEGIN
    -- Xác định số lượng chi tiết sản phẩm cần thêm cho hóa đơn này (từ 2 đến 4)
    SET @count = 2 + FLOOR(RAND() * 3); 

    -- Thêm các chi tiết sản phẩm cho hóa đơn hiện tại
    INSERT INTO ChiTietHoaDon (maHD, maSP, soLuongSanPham, thanhTien)
    SELECT TOP (@count)
        @maHD AS maHD,
        p.maSP,
        ROUND(1 + (RAND() * 3), 0) AS soLuongSanPham, -- Số lượng sản phẩm ngẫu nhiên từ 1 đến 4
        ROUND(1 + (RAND() * 3), 0) * p.gia AS thanhTien -- Tính thành tiền dựa trên số lượng và giá
    FROM 
        SanPham AS p
    ORDER BY 
        NEWID(); -- Chọn sản phẩm ngẫu nhiên

    -- Tiếp tục với hóa đơn tiếp theo
    FETCH NEXT FROM invoiceCursor INTO @maHD;
END

-- Đóng và giải phóng con trỏ
CLOSE invoiceCursor;
DEALLOCATE invoiceCursor;




SELECT DATEPART(MONTH, ngayLapHD) AS Month, SUM(tongTien) AS TotalAmount         
FROM HoaDon
WHERE DATEPART(YEAR, ngayLapHD) = 2024     
GROUP BY DATEPART(MONTH, ngayLapHD)        
ORDER BY  Month ASC;               


SELECT DATEPART(MONTH, ngayLapHD) AS Month, 
       SUM(tongTien - tienGiam) AS TotalAmount         
FROM HoaDon
WHERE DATEPART(YEAR, ngayLapHD) = 2024     
GROUP BY DATEPART(MONTH, ngayLapHD)        
ORDER BY Month ASC;



SELECT COUNT(*) AS TongSanPhamSapHetHang
FROM SanPham
WHERE soLuong < 50;

SELECT SUM([tongTien]) AS TongDoanhThu
FROM HoaDon
WHERE MONTH([ngayLapHD]) = MONTH(GETDATE()) AND YEAR([ngayLapHD]) = YEAR(GETDATE());


INSERT INTO SanPham (maSP, tenSP, ngaySanXuat, ngayHetHan, nhaCungCap, gia, thanhPhan, congDung, hinhAnhSP, maLoaiSP, soLuong, khoiLuong, donViTinh)
VALUES
(, ,, , ,,,, , , , ,)
SELECT TOP 10 sp.tenSP,SUM(cthd.soLuongSanPham) AS tongSoLuongBan 
FROM ChiTietHoaDon cthd
JOIN SanPham sp ON cthd.maSP = sp.maSP
GROUP BY sp.maSP, sp.tenSP
ORDER BY tongSoLuongBan DESC;

SELECT TOP 10 sp.tenSP, SUM(cthd.soLuongSanPham) AS tongSoLuongBan 
FROM ChiTietHoaDon cthd 
JOIN SanPham sp ON cthd.maSP = sp.maSP JOIN HoaDon hd on cthd.maHD = hd.maHD
WHERE hd.ngayLapHD BETWEEN ? AND ? 
GROUP BY sp.maSP, sp.tenSP 
ORDER BY tongSoLuongBan DESC


SELECT TOP 10 sp.tenSP, SUM(cthd.soLuongSanPham) AS tongSoLuongBan 
FROM ChiTietHoaDon cthd 
JOIN SanPham sp ON cthd.maSP = sp.maSP 
JOIN HoaDon hd ON cthd.maHD = hd.maHD
WHERE hd.ngayLapHD BETWEEN ? AND ?
GROUP BY sp.maSP, sp.tenSP 
ORDER BY tongSoLuongBan DESC;

SELECT cthd.[maSP], sp.tenSP,[soLuong],[ngaySanXuat] ,[ngayHetHan],[khoiLuong],[donViTinh],[nhaCungCap],[gia],[thanhPhan],[congDung],[hinhAnhSP],[maLoaiSP],SUM(cthd.soLuongSanPham) AS tongSoLuongBan 
FROM ChiTietHoaDon cthd 
JOIN SanPham sp ON cthd.maSP = sp.maSP 
JOIN HoaDon hd ON cthd.maHD = hd.maHD
WHERE hd.ngayLapHD BETWEEN ? AND ?
GROUP BY sp.maSP, sp.tenSP 
ORDER BY tongSoLuongBan DESC;

SELECT cthd.[maSP], sp.tenSP, [soLuong], [ngaySanXuat], [ngayHetHan], 
       [khoiLuong], [donViTinh], [nhaCungCap], [gia], [thanhPhan], 
       [congDung], [hinhAnhSP], [maLoaiSP], 
       SUM(cthd.soLuongSanPham) AS tongSoLuongBan 
FROM ChiTietHoaDon cthd 
JOIN SanPham sp ON cthd.maSP = sp.maSP 
JOIN HoaDon hd ON cthd.maHD = hd.maHD
WHERE hd.ngayLapHD BETWEEN '2024-01-01' AND '2024-10-09'
GROUP BY sp.maSP, sp.tenSP, [soLuong], [ngaySanXuat], [ngayHetHan], 
         [khoiLuong], [donViTinh], [nhaCungCap], [gia], [thanhPhan], 
         [congDung], [hinhAnhSP], [maLoaiSP]
ORDER BY tongSoLuongBan DESC;


SELECT * FROM SanPham
WHERE [ngayHetHan] < GETDATE()
ORDER BY tenSP

SELECT * FROM SanPham
WHERE [soLuong] = 0
ORDER BY tenSP

SELECT hd.maNV, nv.hotenNV, COUNT(hd.maHD) AS soLuongGiaoDich
FROM HoaDon hd JOIN NhanVien nv ON hd.maNV = nv.maNV
where ngayLapHD BETWEEN ? AND ?
GROUP BY hd.maNV, nv.hotenNV
ORDER BY soLuongGiaoDich DESC;


SELECT MONTH(ngayLapHD) AS Thang,SUM(tongTien - tienGiam) AS DoanhSo
FROM HoaDon
WHERE YEAR(ngayLapHD) = YEAR(GETDATE()) AND trangThai = 1
GROUP BY MONTH(ngayLapHD)
ORDER BY Thang;

SELECT MONTH(ngayLapHD) AS Thang, COUNT(*) AS TongSoGiaoDich
FROM HoaDon
WHERE YEAR(ngayLapHD) = YEAR(GETDATE()) AND trangThai = 1
GROUP BY MONTH(ngayLapHD)
ORDER BY Thang;

SELECT hd.maNV, hotenNV, SUM(tongTien) AS DoanhSo
FROM HoaDon hd join NhanVien nv on hd.maNV = nv.maNV
WHERE YEAR(ngayLapHD) = YEAR(getdate())
GROUP BY hd.maNV, hotenNV
ORDER BY hotenNV;

SELECT nv.maNV, hotenNV, ngayLapHD, hinhThucThanhToan,hd.trangThai,ghiChu, SUM(tongTien) AS DoanhSo
FROM HoaDon hd JOIN NhanVien nv ON hd.maNV = nv.maNV
WHERE YEAR(ngayLapHD) = YEAR(getdate())
GROUP BY nv.maNV, hotenNV,ngayLapHD, hinhThucThanhToan,hd.trangThai,ghiChu
ORDER BY hotenNV;

SELECT DATEPART(MONTH, ngayLapHD) AS Month,SUM(tongTien - tienGiam) AS TotalAmount FROM HoaDon WHERE DATEPART(YEAR, ngayLapHD) = 2024 GROUP BY DATEPART(MONTH, ngayLapHD) ORDER BY Month ASC

SELECT SUM([tongTien]) AS TongDoanhThu FROM HoaDon WHERE MONTH([ngayLapHD]) = 10 AND YEAR([ngayLapHD]) = YEAR(GETDATE())

UPDATE HoaDon
SET tongTien = (
    SELECT SUM(thanhTien)
    FROM chiTietHoaDon
    WHERE chiTietHoaDon.maHD = HoaDon.maHD
)
WHERE maHD IN (
    SELECT DISTINCT maHD
    FROM chiTietHoaDon
);

