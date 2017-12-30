package ir.fallahpoor.ca.featuredcategories.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.fallahpoor.ca.R;
import ir.fallahpoor.ca.featuredcategories.di.DaggerFeaturedCategoriesComponent;
import ir.fallahpoor.ca.featuredcategories.di.FeaturedCategoriesComponent;
import ir.fallahpoor.ca.featuredcategories.di.FeaturedCategoriesModule;
import ir.fallahpoor.ca.featuredcategories.model.CategoryModel;
import ir.fallahpoor.ca.featuredcategories.presenter.FeaturedCategoriesPresenter;

public class FeaturedCategoriesActivity extends
        MvpActivity<FeaturedCategoriesView, FeaturedCategoriesPresenter> implements
        FeaturedCategoriesView {

    @BindView(R.id.loading_layout)
    FrameLayout loadingLayout;
    @BindView(R.id.categories_recycler_view)
    RecyclerView categoriesRecyclerView;
    @Inject
    FeaturedCategoriesPresenter featuredCategoriesPresenter;
    @Inject
    CategoriesAdapter categoriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        injectDependencies();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_featured_categories);
        ButterKnife.bind(this);

        setupRecyclerView();

        getPresenter().getFeaturedCategories();

    }

    @NonNull
    @Override
    public FeaturedCategoriesPresenter createPresenter() {
        return featuredCategoriesPresenter;
    }

    @Override
    public void showLoading() {
        loadingLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
    }

    @Override
    public void error() {
        findViewById(R.id.error_text_view).setVisibility(View.VISIBLE);
    }

    @Override
    public void renderCategories(List<CategoryModel> categories) {
        categoriesRecyclerView.setVisibility(View.VISIBLE);
        categoriesAdapter.setCategoryModelList(categories);
    }

    private void setupRecyclerView() {
        categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        categoriesRecyclerView.setAdapter(categoriesAdapter);
    }

    private void injectDependencies() {
        getComponent().inject(this);
    }

    private FeaturedCategoriesComponent getComponent() {
        return DaggerFeaturedCategoriesComponent
                .builder()
                .featuredCategoriesModule(new FeaturedCategoriesModule(this))
                .build();
    }

}
