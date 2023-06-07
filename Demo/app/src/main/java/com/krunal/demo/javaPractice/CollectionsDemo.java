package com.krunal.demo.javaPractice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionsDemo {
    public static void main(String[] args) {
        // List
        List<Integer> numList = Arrays.asList(1, 7, 5, 2, 0);
//        numList.add(5); UnsupportedOperationException
        numList.set(1, 9);
        System.out.println(numList.getClass().getName() + " " + numList);

        final List<Integer> newNumList = new LinkedList<>(numList);
        newNumList.remove(0);
        System.out.println(newNumList.getClass().getName() + " " + newNumList);
        newNumList.clear();
        newNumList.add(3);
        newNumList.add(2);
        newNumList.add(4);
        newNumList.add(0);

        System.out.println("4 at" + newNumList.indexOf(4));
        Iterator<Integer> iterator = newNumList.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();

        for (int n: numList) {
            System.out.print(n + " ");
        }
        System.out.println();

        newNumList.addAll(numList);

        // Map - calculate occurrence
        Map<Character, Integer> map = new HashMap<>();
        Character[] arr = {'a', 'b', 'a', 'c', 'd', 'c', 'c', 'c'};
        Arrays.stream(arr).forEach(ch -> {
           if (map.containsKey(ch)) {
               map.put(ch, map.get(ch) + 1);
           } else {
               map.put(ch, 1);
           }
        });
        System.out.println(map);
        map.remove('c');
        map.remove('b', 4);
        System.out.println("keys: " + map.keySet());
        System.out.println("values: " + map.values());

        System.out.println(map.getOrDefault('h', 0));
        map.computeIfAbsent('h', character -> {
            System.out.println(character + " is absent");
            return 6;
        });
        map.putIfAbsent('h', 5);

        // Set
        Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 2, 3, 1, 0));
        System.out.println(set);

        // Collection methods
        System.out.println("max: " + Collections.min(newNumList));
        Collections.shuffle(newNumList);
        System.out.println(newNumList);
        Collections.sort(newNumList);
        System.out.println(newNumList);
    }
}
