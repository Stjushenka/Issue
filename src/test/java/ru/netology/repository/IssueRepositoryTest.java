package ru.netology.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.manager.IssueManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IssueRepositoryTest {
    private IssueRepository repository = new IssueRepository();

    private Issue issue1 = new Issue(1, true, "Stju", 3, new HashSet<>(Arrays.asList("label1", "label2", "label3")), new HashSet<>(Arrays.asList("stju", "mari", "max")));
    private Issue issue2 = new Issue(2, false, "Stju", 10, new HashSet<>(Arrays.asList("label1", "label2", "label5")), new HashSet<>(Arrays.asList("stju", "anton", "max")));
    private Issue issue3 = new Issue(3, true, "Misha", 1, new HashSet<>(Arrays.asList("label4", "label5", "label6")), new HashSet<>(Arrays.asList("bob", "alina", "sophi")));
    private Issue issue4 = new Issue(4, false, "Lena", 25, new HashSet<>(Arrays.asList("label3", "label1", "label4")), new HashSet<>(Arrays.asList("mari", "alina", "any")));

    @Test
    void shouldReturnEmptyWhenFindByOpen() {
        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = repository.findOpen();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnEmptyWhenFindByClosed() {
        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = repository.findClosed();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnEmptyIfClose() {
        repository.closeById(1);
        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = repository.findOpen();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnEmptyIfOpen() {
        repository.openById(2);
        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = repository.findOpen();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindOpenIfOpen() {
        repository.save(issue1);
        List<Issue> expected = new ArrayList<>(List.of(issue1));
        List<Issue> actual = repository.findOpen();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnEmptyIfNoOpen() {
        repository.save(issue2);
        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = repository.findOpen();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindClosedIfClosed() {
        repository.save(issue2);
        List<Issue> expected = new ArrayList<>(List.of(issue2));
        List<Issue> actual = repository.findClosed();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnEmptyIfNoClosed() {
        repository.save(issue3);
        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = repository.findClosed();
        assertEquals(expected, actual);
    }

    @Test
    void shouldClose() {
        repository.save(issue1);
        repository.closeById(1);
        List<Issue> expected = new ArrayList<>(List.of(issue1));
        List<Issue> actual = repository.findClosed();
        assertEquals(expected, actual);
    }

    @Test
    void shouldDoNothingIfWrongIdWhenClose() {
        repository.save(issue1);
        repository.closeById(3);
        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = repository.findClosed();
        assertEquals(expected, actual);
    }

    @Test
    void shouldDoNothingIfNothingToClose() {
        repository.save(issue2);
        repository.closeById(2);
        List<Issue> expected = new ArrayList<>(List.of(issue2));
        List<Issue> actual = repository.findClosed();
        assertEquals(expected, actual);
    }

    @Test
    void shouldOpen() {
        repository.save(issue2);
        repository.openById(2);
        List<Issue> expected = new ArrayList<>(List.of(issue2));
        List<Issue> actual = repository.findOpen();
        assertEquals(expected, actual);
    }

    @Test
    void shouldDoNothingIfWrongIdWhenOpen() {
        repository.save(issue1);
        repository.openById(5);
        List<Issue> expected = new ArrayList<>(List.of(issue1));
        List<Issue> actual = repository.findOpen();
        assertEquals(expected, actual);
    }

    @Test
    void shouldDoNothingIfNothingToOpen() {
        repository.save(issue1);
        repository.openById(1);
        List<Issue> expected = new ArrayList<>(List.of(issue1));
        List<Issue> actual = repository.findOpen();
        assertEquals(expected, actual);
    }


    @Test
    void shouldFindOpenIfOpen1() {
        repository.addAll(List.of(issue1, issue2, issue3, issue4));
        repository.remove(issue2);
        List<Issue> expected = new ArrayList<>(List.of(issue1, issue3));
        List<Issue> actual = repository.findOpen();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnEmptyIfNoOpen1() {
        repository.addAll(List.of(issue2, issue4));
        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = repository.findOpen();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindClosedIfClosed1() {
        repository.addAll(List.of(issue1, issue2, issue3, issue4));
        List<Issue> expected = new ArrayList<>(List.of(issue2, issue4));
        List<Issue> actual = repository.findClosed();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnEmptyIfNoClosed1() {
        repository.addAll(List.of(issue1, issue3));
        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = repository.findClosed();
        assertEquals(expected, actual);
    }

    @Test
    void shouldClose1() {
        repository.addAll(List.of(issue1, issue2, issue3, issue4));
        repository.closeById(1);
        List<Issue> expected = new ArrayList<>(List.of(issue1, issue2, issue4));
        List<Issue> actual = repository.findClosed();
        assertEquals(expected, actual);
    }

    @Test
    void shouldDoNothingIfWrongIdWhenClose1() {
        repository.addAll(List.of(issue1, issue2, issue3, issue4));
        repository.closeById(5);
        List<Issue> expected = new ArrayList<>(List.of(issue2, issue4));
        List<Issue> actual = repository.findClosed();
        assertEquals(expected, actual);
    }

    @Test
    void shouldDoNothingIfNothingToClose1() {
        repository.addAll(List.of(issue2, issue4));
        repository.closeById(4);
        List<Issue> expected = new ArrayList<>(List.of(issue2, issue4));
        List<Issue> actual = repository.findClosed();
        assertEquals(expected, actual);
    }

    @Test
    void shouldOpen1() {
        repository.addAll(List.of(issue2, issue4));
        repository.openById(4);
        List<Issue> expected = new ArrayList<>(List.of(issue4));
        List<Issue> actual = repository.findOpen();
        assertEquals(expected, actual);
    }

    @Test
    void shouldDoNothingIfWrongIdWhenOpen1() {
        repository.addAll(List.of(issue1, issue2, issue3, issue4));
        repository.openById(5);
        List<Issue> expected = new ArrayList<>(List.of(issue1, issue3));
        List<Issue> actual = repository.findOpen();
        assertEquals(expected, actual);
    }

    @Test
    void shouldDoNothingIfNothingToOpen1() {
        repository.addAll(List.of(issue1, issue3));
        repository.openById(1);
        List<Issue> expected = new ArrayList<>(List.of(issue1, issue3));
        List<Issue> actual = repository.findOpen();
        assertEquals(expected, actual);
    }
}
