package com.buz.buzqb.service.impl;

import com.buz.buzqb.entity.Item;
import com.buz.buzqb.repository.ItemRepo;
import com.buz.buzqb.service.ItemService;
import java.util.List;
import java.util.Optional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

@Service
@EnableCaching
public class ItemServiceImpl implements ItemService {

  private final ItemRepo itemRepo;

  @Autowired
  public ItemServiceImpl(ItemRepo itemRepo) {
    this.itemRepo = itemRepo;
  }

  @Override
  public List<Item> getAllItem() {
    return itemRepo.findAll();
  }

  @Override
  @Cacheable(value = "item")
  public Optional<Item> getItemById(Long id) {
    var data = itemRepo.findById(id);
    var entity = data.map(item -> Hibernate.unproxy(item, Item.class)).orElse(null);
    return Optional.ofNullable(entity);
  }

  @Override
  @CachePut(value = "item", key = "#item.id")
  public Item saveItem(Item item) {
    return itemRepo.save(item);
  }

  @Override
  @CacheEvict(value = "item", key = "#item.id")
  public Item updateItem(Item item) {
    return itemRepo.save(item);
  }
}
