package ngliaxl.myguidepageindicator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyFragment extends Fragment {

    private int color;

    public static Fragment newInstance(int color) {
        Bundle bundle = new Bundle();
        bundle.putInt("color", color);
        Fragment fragment = new MyFragment();
        fragment.setArguments(bundle);
        return fragment ;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.color = getArguments().getInt("color") ;

        View view = getView().findViewById(R.id.view) ;
        view.setBackgroundColor(getResources().getColor(color));
    }
}