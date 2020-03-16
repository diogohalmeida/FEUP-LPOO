package com.aor.numbers;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ListAggregatorTest {
    List<Integer> list;

    @Before
    public void helper(){
        list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(2);
        list.add(5);
    }

    @Test
    public void sum() {
        ListAggregator aggregator = new ListAggregator(list);

        int sum = aggregator.sum();

        assertEquals(14, sum);
    }

    @Test
    public void max() {
        ListAggregator aggregator = new ListAggregator(list);

        int max = aggregator.max();

        assertEquals(5, max);
    }

    @Test
    public void min() {
        ListAggregator aggregator = new ListAggregator(list);

        int min = aggregator.min();

        assertEquals(1, min);
    }

    @Test
    public void distinct() {
        ListAggregator aggregator = new ListAggregator(list);

        int distinct = aggregator.distinct(new ListDeduplicator(list));

        assertEquals(4, distinct);
    }

    @Test
    public void bug_report_max() {
        List<Integer> bug_list = new ArrayList<>();
        bug_list.add(-1);
        bug_list.add(-4);
        bug_list.add(-5);
        ListAggregator aggregator = new ListAggregator(bug_list);
        int max = aggregator.max();

        assertEquals(-1, max);
    }

    @Test
    public void bug_report_distinct() {
        List<Integer> bug_list = new ArrayList<>();
        bug_list.add(1);
        bug_list.add(2);
        bug_list.add(4);
        bug_list.add(2);
        ListAggregator aggregator = new ListAggregator(bug_list);

        class StubListDeduplicator implements IListDeduplicator{
            @Override
            public List<Integer> deduplicate() {
                List<Integer> deduplicator = new ArrayList<>();
                deduplicator.add(1);
                deduplicator.add(2);
                deduplicator.add(4);
                return deduplicator;
            }
        }


        int distinct = aggregator.distinct(new StubListDeduplicator());

        assertEquals(3, distinct);
    }
}