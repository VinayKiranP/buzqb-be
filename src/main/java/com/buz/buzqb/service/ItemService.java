package com.buz.buzqb.service;

import com.buz.buzqb.entity.Item;
import java.util.List;
import java.util.Optional;

public interface ItemService {

  List<Item> getAllItem();

  Optional<Item> getItemById(Long id);

  Item saveItem(Item item);

  Item updateItem(Item item);
}
