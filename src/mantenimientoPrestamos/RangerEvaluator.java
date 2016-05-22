/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mantenimientoPrestamos;

import com.toedter.calendar.MinMaxDateEvaluator;
import java.util.Date;

/**
 *
 * @author Mac
 */
class RangeEvaluator extends MinMaxDateEvaluator{
    @Override
    public boolean isInvalid(Date date) {
        return !super.isInvalid(date);
    }
}
    

