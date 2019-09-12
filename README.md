# DataBindingList
# Android Data Binding 初识

说到Data Binding我们来科普一些相关的知识点，那就是**Android Jetpack**是谷歌在2018年推出的，Android Jetpack 是一套库和工具指南，可以帮助开发者轻松的编写优质的应用，Jetpack库中的组件可以帮我们遵循最优秀的做法，简化复杂的任务，将精力集中在编写核心的代码上。Jetpack中包含四方面的内容，今天我们主要来看下他的组件库，Jetpack下包含以下组件：

| **Data Bingding** | **数据绑定**                               |
| ----------------- | ------------------------------------------ |
| **Room**          | **数据库**                                 |
| **WorkManager**   | **后台任务管家**                           |
| **Lifecycle**     | **生命周期**                               |
| **Navigation**    | **导航**                                   |
| **Paging**        | **分页**                                   |
| **LiveData**      | **底层数据通知更改视图**                   |
| **ViewModel**     | **以注重生命周期的方式管理界面的相关数据** |

[TOC]



# 一、Data Binding 初识

Data Binding 顾名思义 数据绑定，Android中哪里需要数据绑定，或者说我们的数据在哪里可以看见，那必是UI了，我们的一些数据都是通过Android界面上的UI来展示的，比如一些常见的列表展示一些数据之类的，但是所谓的数据绑定，到底是什么意思呢？别急，数据绑就是将我们的model中的数据跟Android界面上的UI进行一个捆绑，这样我们改变数据或者是界面的改变都不用在频繁的在代码中在进行get或者set了，使得一切看起来都变得智能化，数据绑定的另一个好处是，我们不用在后台再频繁的书写各种findViewById（）之类的代码，数据是绑定在特定的控件上的，所以数据改变只有特定的控件会更新，不会有其他控件更新，也不会出现空指针之类的异常。这样也降低了布局跟逻辑的耦合性，使得代码逻辑更加清晰，是MVVM架构在Android上应用的必要的组件。 数据绑定会大幅的减少Activity fragment等Android界面组件中的代码，使得程序看起来简洁精炼，便于后期的维护。

## 1、Data Binding如何使用

### （1）、环境配置

打开我们的Android Studio，到现在为止，AS已经更新到了3.5版本了，Android版本已经到了Android 10，所以大家还是尽快升级到新版本，新版本解决了之前版本遗留的一些bug，使用起来也更加方便，如果还是停留在老的版本，使用Android Jetpack中的组件或多或少会有些问题，当然那些问题相较于升级就能解决的话，我们也没有必要去探讨解决的方案！

我们新建一个Android工程，打开我们的工程界面，找到我们的app下的build.gradle文件在android的代码块里面加入如下代码->   

```java
 dataBinding {
        enabled = true
    }
```

具体位置如下截图：

```java
apply plugin: 'com.android.application'
android {
    compileSdkVersion 29
    buildToolsVersion "29.0.1"
    defaultConfig {
        applicationId "com.ekwing.newrecyclerviewdemo"
        minSdkVersion 16
        targetSdkVersion 29
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    //对就是这里,就是下面这点代码
    dataBinding {
        enabled = true
    }
}

```

然后clean一下工程，这样我们的工程就完美的支持Data Binding了，怎么样配置是不是很简单！我们接着往下看！

### （2）、开始使用

打开我们的布局文件->

```java
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

</androidx.constraintlayout.widget.ConstraintLayout>
```



我们双击选中我们的ConstraintLayout 标签，然后按下Alt+enter，选择convert to data binding layout，会生成如下的布局：

```java
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <data>

    </data>

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">

    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
```

我们发现，我们的布局外边套了一层layout标签，上面还有了一组data标签，没错，聪明的你一定想到了，这个data标签必然是界面进行数据绑定的关键！那这样就数据绑定了吗？还没有，我们还需要在Activity中做一些基本的操作，打开我们的Activity界面，

```java
 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
    }
```

这是正常的onCreate回调，我们需要对它做一些改变来完成最终的数据绑定，如下：

```java
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;//第一行

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);//第二行
     
    }
}
```

我们可以看到加了两行代码，ActivityMainBinding 这个就是我们的布局界面生成的，你在AS里打会有提示，跟我们的布局文件名字对应，把下划线去掉了！

还把之前的setContentView（）做了一个修改，如上，好了，至此数据绑定，就完成了。

我们简单的写一个button控件，-->

```java
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <data>

    </data>

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">
        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            android:text="测试"
            android:id="@+id/bt_show"/>
        
    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
```

那么我们的点击事件怎么写？

如下：

```java
  binding.btShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"我被点击了",Toast.LENGTH_SHORT).show();
            }
        });
```



我们只是说了怎么使用但是我们还是没有涉及到核心的东西，比如数据 数据 数据，好的，我们接下来就看下布局文件里的数据，

```java
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
xmlns:tools="http://schemas.android.com/tools">

<data>
    <variable
        name="name"
        type="String" />

</data>

<androidx.constraintlayout.widget.ConstraintLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">
    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        android:text="测试"
        android:id="@+id/bt_show"/>
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toBottomOf="@id/bt_show"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        android:layout_marginTop="20dp"
        android:text="@{name}"/>

</androidx.constraintlayout.widget.ConstraintLayout>
</layout>

```

看我们又对布局文件做了一个修改，里面加了点数据，加了个控件，我们看下怎么让这个控件显示出来东西->

```java
 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main6);
        binding.setName("Data");//就这一句

        binding.btShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"我被点击了",Toast.LENGTH_SHORT).show();
            }
        });

    }

```

我们简单的可以通过上面那一句就可以实现将数据在UI上显示出来，是不是很简洁，省去了繁琐的setText，和findViewById（）。

# 二、利用Data Binding，写一个常见的列表页面

activity的布局界面：

```java
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <data>

    </data>

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".mvvmlist.Main5Activity">

        <androidx.recyclerview.widget.RecyclerView
            android:layout_width="match_parent"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            android:layout_height="wrap_content"
            android:id="@+id/rv_list" />

    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>

```

item布局页面：

```java
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <data>
        <import type="com.ekwing.newrecyclerviewdemo.mvvmlist.UserCenter"/>
        <variable
            name="usercenter"
            type="UserCenter" />
    </data>

    <androidx.cardview.widget.CardView
        android:orientation="vertical"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:cardCornerRadius="8dp"
        android:padding="8dp"
        app:contentPaddingLeft="8dp"
        app:contentPaddingBottom="5dp"
        app:contentPaddingRight="8dp">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="300dp"
            android:orientation="vertical">

            <TextView
                android:id="@+id/iv_show_user"
                android:layout_width="80dp"
                android:layout_height="80dp"
                android:layout_gravity="center"
                android:layout_marginTop="10dp"
                android:text="@{usercenter.mImageId}"
                android:background="@drawable/iv_show_bg_circle" />

            <TextView
                android:id="@+id/tv_show"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_gravity="center"
                android:layout_marginTop="20dp"
                android:layout_weight="1"
                android:gravity="center"
                android:text="@{usercenter.mName}"/>

            <Button
                android:id="@+id/bt_next"
                android:layout_width="160dp"
                android:layout_height="40dp"
                android:layout_gravity="center"
                android:text="这是测试的按钮"
                android:background="@drawable/bt_show_bg"
                android:layout_marginTop="20dp"
                android:layout_marginBottom="20dp" />

        </LinearLayout>
        
    </androidx.cardview.widget.CardView>
</layout>

```

没错data标签里的这个正是我们常规写法里的bean，model。

我们再来看下适配器怎么写的：

```java
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<UserCenter> mDataList;

    public RecyclerViewAdapter(List<UserCenter> mDataList) {
        this.mDataList = mDataList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        IteListMvvmBinding binding;
        public ViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding=(IteListMvvmBinding)binding;
        }

        public IteListMvvmBinding getBinding() {
            return binding;
        }
    }

    
    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        IteListMvvmBinding binding= DataBindingUtil.inflate((LayoutInflater.from(parent.getContext())), R.layout.ite_list_mvvm,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        UserCenter userCenter=mDataList.get(position);
        holder.getBinding().setUsercenter(userCenter);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}


```

基本上是常规的写法，

但是这里需要对item进行一个绑定：

```java
  IteListMvvmBinding binding= DataBindingUtil.inflate((LayoutInflater.from(parent.getContext())), R.layout.ite_list_mvvm,parent,false);
        return new ViewHolder(binding);

```

```java
 UserCenter userCenter=mDataList.get(position);
        holder.getBinding().setUsercenter(userCenter);

```

注意从写的方法里的这两块，还有ViewHolder那部分，我们data设置的bean的时候，注意要调用一下：

 holder.getBinding().setUsercenter(userCenter); 该方法，记得将我们的bean类设置上去。

Activity里还是之前的写法，不再给出代码。

完整的demo在 

```url
https://github.com/haibowen/DataBindingList

```





