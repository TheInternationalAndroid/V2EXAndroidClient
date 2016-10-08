package ${packageName};

import android.os.Bundle;

import ${realAppPackage}.databinding.${underscoreToCamelCase(layoutName)}Binding;
import ${packageName}.contract.Dagger${pageName}Contract_Comp;
import ${packageName}.vm.${pageName}VM;
import ${packageName}.vm.module.${pageName}VMModule;
import ${packageName}.contract.${pageName}Contract;
import ${libPackage}.view.base.page.BaseDIActivity;
import ${libPackage}.view.base.view.ILifeCycle;
import javax.inject.Inject;

<#if hasAppBar>
<#else>
import ${superClassFqcn};
</#if>
<#if isNewProject>
import android.view.Menu;
import android.view.MenuItem;
</#if>
<#if applicationPackage??>
import ${realAppPackage}.R;
</#if>

public class ${activityClass} extends BaseDIActivity implements ${pageName}Contract.View{

    @Inject ${pageName}VM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ${underscoreToCamelCase(layoutName)}Binding binding = bindLayout(R.layout.${layoutName});
        binding.setViewModel(viewModel);
    }

<#if isNewProject>
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.${menuName}, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
</#if>

    @Override
    protected ILifeCycle getPageLifeCycle() {
        return viewModel.presenter();
    }

    @Override
    public void buildComp() {
        Dagger${pageName}Contract_Comp
            .builder()
            .activityComp(getActivityComp())
            .${moduleNameUncapFirst}(new ${pageName}VMModule(this))
            .build()
            .inject(this);

    }
}
