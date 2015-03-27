package be.howest.nmct.intentfiltersproject;

import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
//import android.

import java.util.List;

/**
 * Created by Joren on 6/03/2015.
 */
public class Main_Fragment extends Fragment {
    private Button btnLaunch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate de layout
        View v = inflater.inflate(R.layout.fragment_main,container,false);
        btnLaunch = (Button)v.findViewById(R.id.btnLaunch);

        btnLaunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchWithAction(v);
            }
        });

        return v;
    }

    public void launchWithAction(View v)
    {
        Intent intent = new Intent(Constants.ACTION_IMPLY);
        //startActivity(intent);

        // Verify it resolves
        PackageManager packageManager = getActivity().getPackageManager();//PackageManager kan enkel door de mainactivity opgeroepen worden. Aangezien we nu in een fragment zitten moet er "getActivity()" voorstaan.
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        boolean isIntentSafe = activities.size() > 0;

// Start an activity if it's safe
        if (isIntentSafe) {
            startActivity(intent);
        }

    }
}
