package com.nbw.lucene.test;

import java.util.ArrayList;
import java.util.List;

import com.nbw.lucene.util.Pageable;

public class Test {

	
	
	public static void main(String[] args) {
		
	List<Integer> list=new ArrayList<Integer>();
	for(int i=0;i<35;i++)
	{
		list.add(i);
		
	}
		Pageable page=new Pageable(list);
	
		page.setPageSize(30);
		page.setPage(2);
		System.out.println(page.getListForPage().size());
		System.out.println("total"+page.getList().size());
		System.out.println("zhongyema"+page.getMaxPages());
		System.out.println("next"+page.getNextPage());
		System.out.println("当前页"+page.getPageSize());
		
		
	
		
	}
}
