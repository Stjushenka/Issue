package ru.netology.repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor


public class IssueRepository {
    private final List<Issue> items = new ArrayList<>();

    public boolean addAll(List<? extends Issue> items) {
        return this.items.addAll(items);
    }

    public List<Issue> findAll() {
        return items;
    }

    public boolean save(Issue item) {
        return items.add(item);
    }

    public boolean remove(Issue item) {
        return items.remove(item);
    }

    public List<Issue> findOpen() {
        List<Issue> issues = new ArrayList<>();
        for (Issue item : items)
            if (item.isOpen()) {
                issues.add(item);
            }
        return issues;
    }

    public List<Issue> findClosed() {
        List<Issue> issues = new ArrayList<>();
        for (Issue item : items)
            if (!item.isOpen()) {
                issues.add(item);
            }
        return issues;
    }

    public void closeById(int id) {
        for (Issue item : items) {
            if (item.getId() == id && item.isOpen()) {
                item.setOpen(false);
            }
        }
    }

    public void openById(int id) {
        for (Issue item : items) {
            if (item.getId() == id && !item.isOpen()) {
                item.setOpen(true);
            }
        }
    }
}