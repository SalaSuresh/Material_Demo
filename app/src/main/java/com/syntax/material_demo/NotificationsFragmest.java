package com.syntax.material_demo;

import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RemoteViews;

/**
 * Created by sures on 6/8/2016.
 */
public class NotificationsFragmest extends Fragment {
    Button mbutton_default_notification, mbutton_expandable_notification, mbutton_custom_notification;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root_view = inflater.inflate(R.layout.notifications_fragment,container,false);

        //Default Notification
        mbutton_default_notification = (Button) root_view.findViewById(R.id.button_default_notification);
        mbutton_default_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(getActivity())
                                .setSmallIcon(R.drawable.ic_launcher)
                                .setStyle(new NotificationCompat.BigTextStyle().bigText("Android custom notification with multiline feature......."))
                                .setContentTitle("Notification Title");
//                                .setContentText("Notification Text");

                // Creates an explicit intent for an Activity in your app
                Intent resultIntent = new Intent(getActivity(), MainActivity.class);

                // The stack builder object will contain an artificial back stack for the
                // started Activity.
                // This ensures that navigating backward from the Activity leads out of
                // your application to the Home screen.
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(getActivity());

                // Adds the back stack for the Intent (but not the Intent itself)
                stackBuilder.addParentStack(MainActivity.class);

                // Adds the Intent that starts the Activity to the top of the stack
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                mBuilder.setContentIntent(resultPendingIntent);
                NotificationManager mNotificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                // mId allows you to update the notification later on.
                mNotificationManager.notify(0, mBuilder.build());
            }
        });

        //Expandable Notification
        mbutton_expandable_notification = (Button) root_view.findViewById(R.id.button_expandable_notification);
        mbutton_expandable_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBoardingPassNotif("RJY", "KJM");
            }
        });

        //Custom Notification
        mbutton_custom_notification = (Button) root_view.findViewById(R.id.button_custom_notification);
        mbutton_custom_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomNotification();
            }
        });

        return root_view;
    }

    public void showBoardingPassNotif(String origin, String destination) {
        RemoteViews bpView = new RemoteViews(getActivity().getPackageName(), R.layout.expandable_notification_layout);
        bpView.setTextViewText(R.id.origin_text_view, origin);
        bpView.setTextViewText(R.id.destination_text_view, destination);
        NotificationManager nm = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notif = new Notification.Builder(getActivity()).setContentTitle("Boarding pass").setContentText("Click for more info").setSmallIcon(R.drawable.ic_launcher).build();
        notif.flags |= Notification.FLAG_AUTO_CANCEL;
        notif.bigContentView = bpView;
        nm.notify(0, notif);
    }

    public void showCustomNotification(){
        int icon = R.drawable.ic_launcher;
        long when = System.currentTimeMillis();
        Notification notification = new Notification(icon, "Custom Notification", when);

        NotificationManager mNotificationManager = (NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        RemoteViews contentView = new RemoteViews(getActivity().getPackageName(), R.layout.custom_notification_layout);
        contentView.setImageViewResource(R.id.image, R.drawable.ic_launcher);
        contentView.setTextViewText(R.id.title, "Custom notification");
        contentView.setTextViewText(R.id.text, "This is a custom layout");
        notification.contentView = contentView;

        Intent notificationIntent = new Intent(getActivity(), MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getActivity(), 0, notificationIntent, 0);
        notification.contentIntent = contentIntent;

        notification.flags |= Notification.FLAG_AUTO_CANCEL;//FLAG_NO_CLEAR; //Do not clear the notification
        notification.defaults |= Notification.DEFAULT_LIGHTS; // LED
        notification.defaults |= Notification.DEFAULT_VIBRATE; //Vibration
        notification.defaults |= Notification.DEFAULT_SOUND; // Sound

        mNotificationManager.notify(1, notification);
    }
}