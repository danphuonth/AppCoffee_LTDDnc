package com.example.nhom14_quanlycuahangcaphe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {
    ImageView imageView;
    Button button;
    RelativeLayout res;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Order order=new Order();

        HashMap<String,String> a=new HashMap<>();
        a.put("1","lam");
        FirebaseFirestore.getInstance().collection("users").document("1").set(a);
//        res = findViewById(R.id.res);
//        imageView = findViewById(R.id.im);
//        button = findViewById(R.id.button);
//        imageView.setVisibility(View.GONE);
//        res.setVisibility(View.VISIBLE);
//
//        ArrayList<String> arrayList = new ArrayList<>();
//        arrayList.add("a");
//        arrayList.add("b");
//        arrayList.add("c");
//        Toast.makeText(MainActivity2.this, arrayList.indexOf("d")+"", Toast.LENGTH_SHORT).show();

//        ArrayList<Food> foods=new ArrayList<>();
//        Query query= FirebaseDatabase.getInstance().getReference().child("foods");
//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                foods.clear();
//                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    Food food = dataSnapshot.getValue(Food.class);
//
//                    foods.add(food);
//                }
//                Toast.makeText(MainActivity2.this,foods.toString(),Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//    }
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                imageView.setVisibility(View.VISIBLE);
//                res.setVisibility(View.GONE);
//            }
//        });
//        Table table=new Table("2G","tầng 2","",0);
        ArrayList<Food> foods=new ArrayList<>();
  //foods.add(new Food("Lớn",10000,"L"));
//        foods.add(new Food("Phin sữa đá","phinsuada.png",29000,"2","caphe"));
//        foods.add(new Food("Bạc Xĩu Đá","bacxiuda.png",29000,"3","caphe"));
//        foods.add(new Food("Phin Sữa Nóng","phinsuanong.png",29000,"4","caphe"));
//        foods.add(new Food("Phin Đen Nóng","phindennong.png",29000,"5","caphe"));
//        foods.add(new Food("Phindi Kem Sữa","phindikemsua.png",39000,"6","caphe"));
//        foods.add(new Food("Phindi Hạnh Nhân","phindihanhnhan.png",39000,"7","caphe"));
//        foods.add(new Food("Phindi Choco","phindichoco.png",39000,"8","caphe"));
//        foods.add(new Food("Latte","latte.png",54000,"9","caphe"));
//        foods.add(new Food("Americano","americano.png",44000,"10","caphe"));
//        foods.add(new Food("Espresso","espresso.png",44000,"11","caphe"));
//        foods.add(new Food("Cappuchino","cappiccino.png",54000,"12","caphe"));
//        foods.add(new Food("Caramel Macchiato","caramelmachiato.png",59000,"13","caphe"));
//        foods.add(new Food("Mocha Macchiato","mochamachiato.png",59000,"14","caphe"));
//        for (int i=15;i<=18;i++)
//        {
        foods.add(new Food("Bánh phô mai trà xanh","270_crop_PHOMAITRAXANH.jpg",29000,"15","banh"));
        foods.add(new Food("Bánh caramel phô mai","270_crop_CARAMELPHOMAI.jpg",29000,"16","banh"));
        foods.add(new Food("Bánh tiramisu","270_crop_TIRAMISU.jpg",19000,"17","banh"));
        foods.add(new Food("Bánh chuối","270_crop_BANHCHUOI.jpg",19000,"18","banh"));
        foods.add(new Food("Bánh mouse cacao","270_crop_MOUSSECACAO.png",29000,"19","banh"));
        foods.add(new Food("Bánh phô mai chanh dây","270_crop_PHOMAICHANHDAY.jpg",29000,"20","banh"));
        foods.add(new Food("Bánh socola","270_crop_SOCOLAHL.png",29000,"21","banh"));
        foods.add(new Food("Bánh mousse đào","270_crop_MOUSSEDAO.png",29000,"22","banh"));



//        for (Food a:foods) {
//                FirebaseDatabase.getInstance().getReference().child("foods").child(a.foodKey).setValue(a);
//            }
//        }



    }

}