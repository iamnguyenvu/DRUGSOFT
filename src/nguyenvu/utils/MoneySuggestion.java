/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nguyenvu.utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class MoneySuggestion {
    public static List<Integer> suggestAmounts(int tongTien) {
        List<Integer> suggestions = new ArrayList<>();

        suggestions.add(tongTien);

        int nearestRoundedUp = (tongTien % 1000 == 0) ? tongTien : ((tongTien / 1000) + 1) * 1000;
        if (nearestRoundedUp != tongTien && !suggestions.contains(nearestRoundedUp)) {
            suggestions.add(nearestRoundedUp);
        }

        int nearestRoundedDown = (tongTien / 1000) * 1000;
        if (nearestRoundedDown != tongTien && !suggestions.contains(nearestRoundedDown)) {
            suggestions.add(nearestRoundedDown);
        }

        int[] increments;
        if (tongTien <= 500000) {
            increments = new int[]{1000, 2000, 5000, 10000, 20000, 50000, 100000, 200000, 500000};
        } else if (tongTien <= 1000000) {
            increments = new int[]{1000, 2000, 5000, 10000, 20000, 50000, 100000, 200000, 500000, 1000000};
        } else {
            increments = new int[]{1000, 2000, 5000, 10000, 20000, 50000, 100000, 200000, 500000, 1000000, 2000000};
        }

        for (int increment : increments) {
            int nextSuggestion = ((tongTien + increment - 1) / increment) * increment;
            if (!suggestions.contains(nextSuggestion)) {
                suggestions.add(nextSuggestion);
            }
            if (suggestions.size() >= 6) break;
        }

        return suggestions;
    }

}
