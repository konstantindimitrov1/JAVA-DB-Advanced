package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findAllByNameStartsWith(String str);

    List<Ingredient> findAllByNameInOrderByPrice(Collection<String> name);

    @Query("delete from Ingredient i where i.name like :name")
    @Modifying
    void deleteIngredientByGivenName(@Param(value = "name") String ingredientName);

    @Query("update Ingredient i set i.price = i.price * 1.1")
    @Modifying
    void increasePriceOfAllIngredientsByTenPercent();

    @Query("update Ingredient i set i.price = i.price * 1.1 where i.name in :ingredients")
    @Modifying
    void increasePriceOfGivenIngredients(@Param(value = "ingredients") List<String> ingredients);
}
