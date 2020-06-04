package com.problem1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SortUsingStreams {
	
	public static void print(List<Order> olist) {
		List<String> strlist=new ArrayList<String>();
		
		//Adding all into single Stream 
		for(Order ord:olist) {
			strlist= Stream.concat(strlist.stream(), ord.getOrderList().stream())
                    .collect(Collectors.toList());
		}

		Map<String, Long> finalMap = new LinkedHashMap<>();
		
		//Making a list into Map with item and their count
		Map<String, Long> result =strlist.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
      

        //Sort the map obtained in previous step and taking away only top 2 from them
        result.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(2)
                .forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));

		
		//Printing in the appropriate way 
		
		for(Entry<String,Long> ent:finalMap.entrySet()) {
		System.out.println(ent.getKey()+"-> usageCount :"+ent.getValue()+"; Corresponding orders:");
			for(Order ord:olist) {
				if(ord.getOrderList().contains(ent.getKey())) System.out.print(ord.getOrdername()+" ");
			}System.out.println(" ");
		}
	}
	
	
		
	public static void main(String[] args) {
		
		//Creating Orders and giving Inputs
		
		List<String> order1=Arrays.asList("item1","item2");
		Order ord1=new Order();
		ord1.setOrdername("Order1");
		ord1.setOrderList(order1);
		
		List<String> order2=Arrays.asList("item3","item4");
		Order ord2=new Order();
		ord2.setOrderList(order2);
		ord2.setOrdername("Order2");
		
		List<String> order3=Arrays.asList("item3","item4");
		Order ord3=new Order();
		ord3.setOrderList(order3);
		ord3.setOrdername("Order3");
		
//		List<String> order4=Arrays.asList("item2","item4");
//		Order ord4=new Order();
//		ord4.setOrderList(order4);
//		ord4.setOrdername("Order4");
		
		//Add all orders to List
		List<Order> olist=new ArrayList<>();
		olist.add(ord1);
		olist.add(ord2);
		olist.add(ord3);
//		olist.add(ord4);
		
		//Call the function and displaying result
		SortUsingStreams.print(olist);
	}
}
