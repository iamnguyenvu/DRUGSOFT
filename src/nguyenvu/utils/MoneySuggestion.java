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

        int nearestRounded = (tongTien % 10000 == 0) ? tongTien : ((tongTien / 10000) + 1) * 10000;
        if (nearestRounded != tongTien) {
            suggestions.add(nearestRounded);
        }

        int[] increments;
        if (tongTien <= 500000) {
            increments = new int[]{50000, 100000, 200000, 500000};
        } else if (tongTien <= 1000000) {
            increments = new int[]{100000, 200000, 500000, 1000000};
        } else {
            increments = new int[]{200000, 500000, 1000000, 2000000};
        }

        for (int increment : increments) {
            int nextSuggestion = ((tongTien + increment - 1) / increment) * increment;
            if (!suggestions.contains(nextSuggestion)) {
                suggestions.add(nextSuggestion);
            }
        }

        while (suggestions.size() < 6) {
            suggestions.add(suggestions.get(suggestions.size() - 1));
        }

        return suggestions;
    }

}
