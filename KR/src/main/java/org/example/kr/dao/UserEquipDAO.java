package org.example.kr.dao;

import java.util.List;

public interface UserEquipDAO {
    List<Integer> getWishList(int id);
    Boolean addToWishList(int equipId, int userId);
    Boolean removeFromWishList(int equipId, int userId);
}
