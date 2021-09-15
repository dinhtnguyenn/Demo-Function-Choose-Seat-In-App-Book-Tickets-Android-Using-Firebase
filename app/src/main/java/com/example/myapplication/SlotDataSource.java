package com.example.myapplication;


import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;

public class SlotDataSource {
    private static final DatabaseReference sRef = FirebaseDatabase.getInstance().getReference();
    private static String Day, Film, Time, Seat;

    public static void saveSlot(Slot slot) {
        sRef.child("film").child("14-09-2021").child("Film A").child("08:00").child(slot.getSeat()).setValue(slot);
    }

    public static SlotListener addSlotListener(SlotCallback slotCallback) {
        SlotListener slotListener = new SlotListener(slotCallback);
        sRef.child("film").child("14-09-2021").child("Film A").child("08:00").addChildEventListener(slotListener);
        return slotListener;
    }

    public static void stopListener(SlotListener mListener) {
        sRef.removeEventListener(mListener);
    }

    public static class SlotListener implements ChildEventListener {
        private SlotCallback callback;

        public SlotListener(SlotCallback callback) {
            this.callback = callback;
        }

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            Slot slot = dataSnapshot.getValue(Slot.class);

            if (callback != null) {
                callback.onSlotChange(slot);
            }
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            Slot data = dataSnapshot.getValue(Slot.class);

            if (callback != null) {
                callback.onSlotChange(data);
            }
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.e("child cancel ", "");
        }

    }

    public interface SlotCallback {
        void onSlotChange(Slot slot);
    }
}
