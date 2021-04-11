/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtn.utilities;

import java.io.Serializable;

/**
 *
 * @author minhv
 */
public class PageUtils implements Serializable {
    public static int caculatePageRequired(int totalQuestion, int questionPerPage) {
        int totalPage;
        
        if (totalQuestion % questionPerPage != 0)
            totalPage = (totalQuestion / questionPerPage) + 1;
        else
            totalPage = totalQuestion / questionPerPage;
        return totalPage;
    }
}
