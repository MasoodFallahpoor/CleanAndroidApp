package ir.fallahpoor.ca.data.repository;

import java.util.List;

import io.reactivex.Observable;
import ir.fallahpoor.ca.data.WebServiceFactory;
import ir.fallahpoor.ca.data.entity.CategoryEntity;
import ir.fallahpoor.ca.data.entity.CategoryEntityDataMapper;
import ir.fallahpoor.ca.domain.Category;
import ir.fallahpoor.ca.domain.repository.CategoryRepository;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class CategoryRepositoryImpl implements CategoryRepository {

    private CategoryEntityDataMapper categoryEntityDataMapper;

    public CategoryRepositoryImpl(CategoryEntityDataMapper categoryEntityDataMapper) {
        this.categoryEntityDataMapper = categoryEntityDataMapper;
    }

    @Override
    public Observable<List<Category>> getCategories() {

        CategoriesWebService categoriesWebService = WebServiceFactory.createService(
                CategoriesWebService.class);

        return categoriesWebService.getCategories().map(
                categoryEntityDataMapper::transform);

    }

    private interface CategoriesWebService {
        @GET("category/supercategories")
        Observable<List<CategoryEntity>> getCategories();
    }

}