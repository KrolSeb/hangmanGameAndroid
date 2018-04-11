package wisielec.wisielec.com.repository;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import wisielec.wisielec.com.activity.SignInActivity;
import wisielec.wisielec.com.domain.User;

/**
 * Created by Sebastian on 2018-01-17.
 */

public class UserRepository {
    private static final String TAG = "UserRepository";
    protected static User receivedUser;


    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase firebaseDatabase;


    public static User retrieveUserFromDatabase() {
        receivedUser = new User();
        DatabaseReference mDatabaseStatic = FirebaseDatabase.getInstance().getReference("users");
        Query queryRef = mDatabaseStatic.orderByChild("actuallyLogged").equalTo(true);
        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                receivedUser.setAvatarURL(dataSnapshot.getValue(User.class).getAvatarURL());
                receivedUser.setUserName(dataSnapshot.getValue(User.class).getUserName());
                receivedUser.setRankingPosition(dataSnapshot.getValue(User.class).getRankingPosition());
                receivedUser.setRank(dataSnapshot.getValue(User.class).getRank());
                receivedUser.setPoints(dataSnapshot.getValue(User.class).getPoints());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                receivedUser.setAvatarURL(dataSnapshot.getValue(User.class).getAvatarURL());
                receivedUser.setUserName(dataSnapshot.getValue(User.class).getUserName());
                receivedUser.setRankingPosition(dataSnapshot.getValue(User.class).getRankingPosition());
                receivedUser.setRank(dataSnapshot.getValue(User.class).getRank());
                receivedUser.setPoints(dataSnapshot.getValue(User.class).getPoints());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return receivedUser;
    }

    public void registerNewUser(final Context context, final User user) {

        firebaseAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser authenticatedUser = firebaseAuth.getCurrentUser();
                            firebaseDatabase = FirebaseDatabase.getInstance();

                            DatabaseReference databaseReference = firebaseDatabase.getReference();
                            databaseReference.child("users").push().child(authenticatedUser.getUid());

                            user.setPassword("");
                            databaseReference.push().setValue(user);

                            Intent intent = new Intent(context, SignInActivity.class);
                            context.startActivity(intent);
                            ((Activity) context).finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        }
                    }
                });
    }


    public void addUserToDatabase(User user) {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").push().setValue(user);
    }
}
