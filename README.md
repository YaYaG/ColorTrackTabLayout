## 效果图 

<img src="https://raw.githubusercontent.com/YaYaG/ColorTrackTabLayout/master/img/a.gif" width="279" height="441" align="middle" />

### app项目中build.gradle添加：

```
    implementation 'com.yayaG.colorTrackTabLayout:colortracklayout:1.0.0'
```

### 在主项目中的build.gradle添加

```
    maven { url 'https://dl.bintray.com/wangjinya/maven' }
```

```
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://maven.google.com' }
        maven { url 'https://dl.bintray.com/wangjinya/maven' }
    }
}

```

## Adapter 可继承：
```
    ColorTrackFragmentAdapter
    
    ColorTrackAdapter
```

## xml中：

```
    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:padding="10dp"
        tools:context=".MainActivity">
    
        <com.jackwang.colortracklayout.ColorTrackLayout
            android:id="@+id/tab_layout"
            android:layout_width="match_parent"
            android:layout_height="50dp"
            app:tabMode="scrollable"
            />
    
        <androidx.viewpager.widget.ViewPager
            android:id="@+id/viewpager"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            android:layout_weight="1"/>
    
    </LinearLayout>
```

## 怎么用呢？
<img src="https://raw.githubusercontent.com/YaYaG/ColorTrackTabLayout/master/img/a.png" width="200" height="200" align="middle" />

### 用法很简单。可以直接继承
```
public class MyAdapter extends ColorTrackFragmentAdapter {
    private List<String> titles = new ArrayList<>();
    .....
   
    @Nullable
    @Override
    public List<String> getTitles() {
        return titles;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
```

### Activity中调用：

```
    MyAdapter adapter = new MyAdapter(getSupportFragmentManager(), this);
    mViewPager.setAdapter(adapter);
    
    //默认颜色和字体滑动颜色
    mTabLayout.setNormalColorAndSelectedColor(Color.BLACK, Color.GREEN);
    //字体大小
    mTabLayout.setTabLayoutTextSize(18);
    
    mTabLayout.setupWithViewPager(mViewPager, adapter);
```

<img src="https://raw.githubusercontent.com/YaYaG/ColorTrackTabLayout/master/img/b.png" width="220" height="210" align="middle" />

### 基本就是这样，喜欢就点个star。你的赞美是我的努力源泉。