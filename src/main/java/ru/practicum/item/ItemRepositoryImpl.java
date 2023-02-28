package ru.practicum.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
public class ItemRepositoryImpl implements ItemRepository {

    private List<Item> items;
    private long itemId;

    @Autowired
    public ItemRepositoryImpl() {
        this.items = new ArrayList<>();
        this.itemId = 0;
    }

    private long setItemId() {
        itemId++;
        return itemId;
    }

    @Override
    public List<Item> findByUserId(long userId) {
        List<Item> findItems = new ArrayList<>();
        for (Item findItem : items) {
            if (findItem.getUserId() == userId) {
                findItems.add(findItem);
            }
        }
        return findItems;
    }

    @Override
    public Item save(Item item) {
        long addedItemId = setItemId();
        item.setId(addedItemId);
        items.add(item);
        return getItemById(addedItemId);
    }

    @Override
    public void deleteByUserIdAndItemId(long userId, long itemId) {
        Iterator<Item> itemIterator = items.iterator();
        while(itemIterator.hasNext()) {
            Item item = itemIterator.next();
            if (item.getId() == itemId && item.getUserId() == userId) {
                itemIterator.remove();
            }
        }
    }

    public Item getItemById(long itemId) {
        for (Item item : items) {
            if (item.getId() == itemId) {
                return item;
            }
        }
        return null;
    }
}
