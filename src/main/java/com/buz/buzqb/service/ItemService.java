package com.buz.buzqb.service;

import com.buz.buzqb.entity.Item;
import java.util.List;
import java.util.Optional;

public interface ItemService {

  List<Item> getAllItem();

  Optional<Item> getItemById(Long id);

  Item saveItem(Item subCategory);

  Item updateItem(Item subCategory);
}
