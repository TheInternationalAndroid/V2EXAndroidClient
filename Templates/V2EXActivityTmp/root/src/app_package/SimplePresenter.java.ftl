package ${packageName}.presenter;

import ${libPackage}.presenter.BasePresenter;
import ${packageName}.contract.${pageName}Contract;
import com.squareup.leakcanary.RefWatcher;

import javax.inject.Inject;

public class ${pageName}P extends BasePresenter implements ${pageName}Contract.Presenter {

    @Inject
    ${pageName}P(RefWatcher refWatcher) {
        super(refWatcher);
    }

}
