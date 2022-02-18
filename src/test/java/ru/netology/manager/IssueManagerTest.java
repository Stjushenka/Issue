package ru.netology.manager;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IssueManagerTest {

    private IssueRepository repository = new IssueRepository();
    private IssueManager manager = new IssueManager(repository);

    private Issue issue1 = new Issue(1, true, "Stju", 3, new HashSet<>(Arrays.asList("label1", "label2", "label3")), new HashSet<>(Arrays.asList("stju", "mari", "max")));
    private Issue issue2 = new Issue(2, false, "Stju", 10, new HashSet<>(Arrays.asList("label1", "label2", "label5")), new HashSet<>(Arrays.asList("stju", "anton", "max")));
    private Issue issue3 = new Issue(3, true, "Misha", 1, new HashSet<>(Arrays.asList("label4", "label5", "label6")), new HashSet<>(Arrays.asList("bob", "alina", "sophi")));
    private Issue issue4 = new Issue(4, false, "Lena", 25, new HashSet<>(Arrays.asList("label3", "label1", "label4")), new HashSet<>(Arrays.asList("mari", "alina", "any")));

    @Test
    void shouldReturnEmptyIfNothingToSortByNew() {
        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = manager.sortByNewest();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnEmptyIfNothingToSortByOld() {
        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = manager.sortByOldest();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnEmptyWhenFindByAuthor() {
        Collection<Issue> expected = new ArrayList<>();
        Collection<Issue> actual = manager.findByAuthor("Stju");
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnEmptyWhenFindByLabel() {
        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = manager.findByLabel(new HashSet<String>(Arrays.asList("label1")));
        assertEquals(expected, actual);
    }


    @Test
     void ReturnEmptyWhenFindByAssignee() {
        Collection<Issue> expected = new ArrayList<>();
        Collection<Issue> actual = manager.findByAssignee(new HashSet<String>(Arrays.asList("stju")));
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnOneToSortByNew() {
        manager.issueAdd(issue1);
        List<Issue> expected = new ArrayList<>(List.of(issue1));
        List<Issue> actual = manager.sortByNewest();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnOneToSortByOld() {
        manager.issueAdd(issue1);
        List<Issue> expected = new ArrayList<>(List.of(issue1));
        List<Issue> actual = manager.sortByOldest();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByAuthor() {
        manager.issueAdd(issue1);
        Collection<Issue> expected = new ArrayList<>(List.of(issue1));
        Collection<Issue> actual = manager.findByAuthor("Stju");
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnEmptyIfNotAuthor() {
        manager.issueAdd(issue1);
        Collection<Issue> expected = new ArrayList<>();
        Collection<Issue> actual = manager.findByAuthor("Bob");
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByLabel() {
        manager.issueAdd(issue3);
        Collection<Issue> expected = new ArrayList<>(List.of(issue3));
        Collection<Issue> actual = manager.findByLabel(new HashSet<String>(Arrays.asList("label4")));
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnEmptyIfNoLabel1() {
        manager.issueAdd(issue1);
        Collection<Issue> expected = new ArrayList<>();
        Collection<Issue> actual = manager.findByLabel(new HashSet<String>(Arrays.asList("label25")));
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByAssignee() {
        manager.issueAdd(issue1);
        Collection<Issue> expected = new ArrayList<>(List.of(issue1));
        Collection<Issue> actual = manager.findByAssignee(new HashSet<String>(Arrays.asList("stju")));
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnEmptyWhenFindByAssignee() {
        manager.issueAdd(issue1);
        Collection<Issue> expected = new ArrayList<>();
        Collection<Issue> actual = manager.findByAssignee(new HashSet<String>(Arrays.asList("emma")));
        assertEquals(expected, actual);
    }

    @Test
    void shouldAddByOne() {
        manager.issueAdd(issue1);
        manager.issueAdd(issue2);
        List<Issue> expected = new ArrayList<>(List.of(issue1, issue2));
        List<Issue> actual = manager.getAll();
        assertEquals(expected, actual);
    }

    @Test
    void shouldSortByNew() {
        manager.addAll(List.of(issue1, issue2, issue3));
        List<Issue> expected = new ArrayList<>(List.of(issue3, issue1, issue2));
        List<Issue> actual = manager.sortByNewest();
        assertEquals(expected, actual);
    }

    @Test
    void shouldSortByOld() {
        manager.addAll(List.of(issue1, issue2, issue3));
        List<Issue> expected = new ArrayList<>(List.of(issue2, issue1, issue3));
        List<Issue> actual = manager.sortByOldest();
        assertEquals(expected, actual);
    }

    @Test
    void FindByAuthor() {
        manager.addAll(List.of(issue1, issue2, issue3, issue4));
        List<Issue> expected = new ArrayList<>(List.of(issue1, issue2));
        List<Issue> actual = manager.findByAuthor("Stju");
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnEmptyIfNoAuthor() {
        manager.addAll(List.of(issue1, issue2, issue3, issue4));
        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = manager.findByAuthor("Bob");
        assertEquals(expected, actual);
    }

    @Test
    void FindByLabel() {
        manager.addAll(List.of(issue1, issue2, issue3, issue4));
        List<Issue> expected = new ArrayList<>(List.of(issue1, issue4));
        List<Issue> actual = manager.findByLabel(new HashSet<String>(Arrays.asList("label3")));
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnEmptyIfNoLabel() {
        manager.addAll(List.of(issue1, issue2, issue3, issue4));
        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = manager.findByLabel(new HashSet<String>(Arrays.asList("label25")));
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByAssignee1() {
        manager.addAll(List.of(issue1, issue2, issue3, issue4));
        List<Issue> expected = new ArrayList<>(List.of(issue3, issue4));
        List<Issue> actual = manager.findByAssignee(new HashSet<String>(Arrays.asList("alina")));
        assertEquals(expected, actual);
    }


    @Test
    void shouldReturnEmptyWhenFindByAssignee1() {
        manager.addAll(List.of(issue1, issue2, issue3, issue4));
        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = manager.findByAssignee(new HashSet<String>(Arrays.asList("Emma")));
        assertEquals(expected, actual);
    }

}