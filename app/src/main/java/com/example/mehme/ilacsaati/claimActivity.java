package com.example.mehme.ilacsaati;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class claimActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    NavigationView nv;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    TextView txBilgi;
    Calendar c;
    AlarmManager mgrAlarm = null;
    ArrayList<String> bilgiler = new ArrayList<>();
    ImageView iv;
    Animation fromBottom, fromTop;
    boolean acikmi=false;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerClaim);
        txBilgi = (TextView) findViewById(R.id.herGunBilgiTx);
        iv = (ImageView) findViewById(R.id.ivX);
        fromBottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        fromTop = AnimationUtils.loadAnimation(this, R.anim.fromtop);
        iv.setAnimation(fromBottom);
        iv.setAnimation(fromTop);


        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.ac, R.string.kapa);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout.requestLayout();

        nv = (NavigationView) findViewById(R.id.naviSearch);
        nv.setItemIconTintList(null);
        nv.setNavigationItemSelectedListener(this);
        nv.bringToFront();

        nv.setItemIconTintList(null);
        nv.setNavigationItemSelectedListener(this);
        nv.bringToFront();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_menu_claim);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setSelectedItemId(R.id.istekleriniz);
        bottomNavigationView.setSelectedItemId(5);
        mgrAlarm = (AlarmManager) this.getSystemService(ALARM_SERVICE);

        bilgiler.add("Kanser hastalıklarının yüzde 30'dan fazlası kolaylıkla önlenebilir. İçkiden, sigaradan uzak durur, sağlıklı beslenir ve fiziksel aktiviteler uygularsanız. Hiç \"kalp kanseri\" diye bir şey duymadınız değil mi? Çünkü kalp hücreleri bölünmez..");
        bilgiler.add("Kalp demişken onun ne kadar harika bir organ olduğunu şu özelliğiyle bir kez daha hatırlayalım: Kalbin kendi elektrik santrali vardır. Vücuttan ayırsanız bile atmaya devam eder..");
        bilgiler.add("Her gece yedi saatten az uyumak, ömrünüzü kısaltır. Çocuklar ve gençler için bu süre dokuz saat veya biraz fazlası olmalıdır..");
        bilgiler.add("Gazlı, şekerli, asitli hazır içecekler, diyabete yakalanma ihtimalinizi yüzde 22 artırır. Böyle bir bardak içecek, on çay kaşığı şeker içerir. Her Amerikalı, bunlardan yılda 500 şişe tüketir. Ve sadece içeceklerden yılda 25 kilo şeker almış olur. Diyabet, körlüğün de en büyük sebeplerinden biridir..");
        bilgiler.add("Sağ elini kullanan kişiler, solaklardan ortalama dokuz yıl daha çok yaşamaktadır..");
        bilgiler.add("Makinelerde yapay olarak bronzlaşmak, sigaradan kaynaklanan akciğer kanserinden daha büyük sorunlar yaratır..");
        bilgiler.add("Yüz kez gülmek, sabit bisiklette 15 dakika pedal çevirmeye bedel bir egzersizdir. ABD/Kaliforniya'daki Loma Linda School of Public Health'den Doktor Lee Berk, gülmenin stres hormonları seviyesini düşürdüğünü, bağışıklık sistemini güçlendirdiğini belirtmektedir. Bu konuda en şanslı kişiler bebeklerdir. Günde 300 kez gülerler. Yetişkinlerde ise bu sayı, 15-100 arasıdır..");
        bilgiler.add("İngiliz bahçeleri dünyaca ünlüdür. Bu bahçelerin, bir zamanlar tedavi amaçlı kullanıldığını biliyor muydunuz? Bilim adamı John Ferriar, rengarenk çiçeklerin görüntü ve kokularının, kalp ritminde düzelmelere yardımcı olduğunu 1799'da keşfetmişti..");
        bilgiler.add("Bazı fast-food restoranlarda salatalarda kullanılan soslar, hamburgerin kendisinden daha şişmanlatıcı olabilmektedir. Bir Amerikalı hayatı boyunca bin 800 kez fast-food restorana gider..");
        bilgiler.add("Hastalıkların sadece öksürmek ve hapşırmaktan bulaştığını sanmayın. Bir kapı kolundan kapacağınız bir bakteri, 24 saatte sekiz milyondan fazla çoğalır. Hapşırdığınızda yüz binlerce virüsü, yedi metreye yayarsınız..");
        bilgiler.add("Her gün egzersiz yapmalısınız. 30 dakika kadar. Hareketsiz kalmak ölüme neden olabilir. Hem de sigaradan kaynaklanan ölümler kadar. Her gün üç saatten fazla oturmak, ömrünüzü iki yıl azaltır..");
        bilgiler.add("Yürüyüş yapmak, akciğer kanserine yakalanma ihtimalini yüzde 25 düşürür..");
        bilgiler.add("Dört santimetreden daha yüksek topuklu ayakkabılar, sadece ayak bileği kırılmalarına ve burkulmalara sebep olmaz. Dizleri ve sırtı da olumsuz etkiler..");
        bilgiler.add("Sakalınız varsa onları ihmal etmeyin. Her gün temizlemeli ve fazla kılları almalısınız. Sakallar, mikrop ve bakteri yuvalarıdır..");
        bilgiler.add("Mutlu olmak, rahat, tasasız, stressiz yaşamak, aşık olmak gibi güçlü duygulara sahip olmak, sağlıklı olmanın en önemli anahtarlarıdır..");
        bilgiler.add("Güne sağlıklı bir kahvaltıyla başlamanın ne kadar önemli olduğunu birçok kez duymuşsunuzdur.  Kahvaltı, metabolizmayı hızlandırmanın yanı sıra kan şekeri seviyenizi dengeleyerek öğlen yemeğinde fazla yemenizi engeller.  Yapılan çalışmalar kahvaltı yapan bireylerin günün diğer saatlerinde daha sağlıklı beslendiklerini ve daha az kalori aldıklarını göstermektedir.  Eğer özel bir davet öncesinde birkaç kilo kaybetmek istiyorsanız; kahvaltınızın kadınlarda 250 kalori- erkeklerde 350 kaloriyi geçmemesine ve vücudunuza yeterli enerjiyi vermesi için uzun süre tokluk hissi sağlayan protein (yumurta, peynir, süt, yoğurt) ile sağlıklı karbonhidratlar (tam tahıllar, meyve, sebze) kombinasyonunu içermesine özen gösterin.  ");
        bilgiler.add("Meyveli sodalar, şekerli-gazlı içecekler, meyve suları ve kremalı kahveler tanıdık mı geliyor? Yüksek kalori içeren bu içeceklerden uzak durup onun yerine ‘0’ kalori olan SU içmeye ne dersiniz? Düşünün; her gün içtiğiniz 2 kutu şekerli-gazlı içeceği kaldırmanız ay sonunda yaklaşık 7500 kalori kar etmeniz ve 1 kiloya elveda demeniz anlamına gelir. Ne kadar basit değil mi sadece içtiklerimize dikkat ederek ayda 1 kilo verilebilir. Aynı zamanda 1 kutu gazlı içecekte 12 küp şeker olduğundan yola çıkarsak iki kutu içtiğinizde ayda maalesef 720 küp şeker vücudunuza giriyor ve kilo aldırmanın dışında hücrelerinizi yaşlandırıyor demektir.");
        bilgiler.add("Makarna, ekmek ve pirinç gibi besinleri doğal olarak yemeye eğilimliyiz çünkü karbonhidratlar mutlu olmamızı sağlayan serotonin kimyasalının artmasını sağlarlar. Ancak forma girmek istiyorsanız biraz daha özen göstermeniz ve nişasta alımında ölçülü olmanız gerekir.  Yapılan bir çalışmada; karbonhidrat isteği yüksek olanların diğer bireylere oranla günde  800 veya daha fazla kalori aldığı ve fazla kilolu ya da obez bireyler olduğu ortaya çıkmıştır.  Favori sandviçinizi tek ekmekle tercih ederek, ana yemek olarak koca bir tabak kremalı makarna veya risotto yemek yerine yağsız etinizin yanına kadınlar için 2-3 kaşık, erkekler için 4-5 kaşık makarna/pilav alarak,  nişasta alımınızı azaltabilirsiniz. ");
        bilgiler.add("Tufts Üniversitesi’nde yapılan çalışmalar, evde yemeğini kendi yapıp yemek yerine restoranlarda yemek yemeği seçenlerin %33 daha fazla kalori aldığını gösteriyor. Restoran yemekleri çoğu zaman oldukça büyük porsiyonlarda ve yağ içerikleri yüksek olduğundan sandığınızdan daha çok kalori yüklüdür.  Modern hayatın getirileri içinde evde yemek pişirmek zor olabilir ancak öğle yemeğinizi restoranlarda yeme zorunluluğunuz varsa en azından akşam yemekleri için evi tercih edin. ");
        bilgiler.add("Sık ara öğün yapmak metabolizmayı canlı tutar ancak hızlı çözümler arıyorsanız üç ana öğün ve acıktığınız herhangi bir saatte tek bir ara öğün de yeterli gelebilir. Diyet programlarının kişiye özel hazırlanmasının esas sebeplerinden biri de budur. Her bireyin farklı metabolizması, özellikleri vardır ve bireye bağlı çözümler geliştirmek,  hayatı karmaşıklaştırmanın aksine kolaylaştırmak önemlidir.  Ara öğün seçimleri kadınlar için 150-200 kalori, erkekler için 200-250 kalori sihirli etki göstermektedir.  Atıştırmaya ihtiyaç duyarsanız; dilimlenmiş ve limonlanmış salatalık, havuç, kuşkonmaz imdadınıza yetişecektir. \n");
        bilgiler.add("Akşam yemeği sonrasında gece yaşanan yeme ataklarının asıl suçlusu duygularımız! Sıkıldınız, üzüldünüz yada sevindiniz… Duygularınız sizi yemeğe teşvik edebilir. Bazen su içmek için bile olsa mutfağa gitmek tezgahın üzerinde size göz kırpan bir parça çikolatayı mideye indirmek anlamına gelebilir bu nedenle en iyi sonuç için su şişenizi yanınıza alın. En geç 22.00 itibariyle de mutfağınızın ışıklarını söndürün ve sabaha kadar uğramayın. Rahatlamaya mı ihtiyacınız var? Çubuk tarçın ilaveli rezene, beyaz çay ve melisa çayını deneyin. ");
        bilgiler.add("Özel bir davet için Küçük Siyah Elbisenizi & İtalyan Kesim Takımınızı giymeye sayılı günler mi kaldı? O zaman yürümeye başlayın böylece metabolizmanız yediklerini yak komutunu alacak ve harekete geçecek. Egzersiz aynı zamanda enerji seviyenizi de yükseltecek. Haftada beş gün bir saat egzersiz yapmanın vücudunuzda gerçekleştireceği değişikliği eminim sizde şaşıracaksınız.");
        bilgiler.add("Katılmanız gereken önemli gün geldiyse sodyum alımınıza ekstra özen gösterin. Sodyum, vücutta su tutulumunu sağlayarak ödem yapar ve kendinizi şişmiş hissedersiniz.  Paketlenmiş ve işlem görmüş ürünlerden uzak durup, yemeklerinizi pişirirken tuz yerine baharat kullanın. Gün içinde ananas, semizotu, kabak, maydanoz tüketin.");
        bilgiler.add("Kadınlarda durum daha zor.  Zayıflama takıntısı ve medya baskısı çok küçük yaşta etkiliyor. Çok korkutucu ancak 3 yaşındaki kızlar, şimdiden zayıf olmak düşüncesindeler. Güncel araştırmalar, bazı kızların 6 yaşına geldiklerinde kilolarını kontrol etmek için yediklerini kısıtladıklarını gösteriyor. Bu durum, kadınlar üzerinde ciddi baskılar yaratarak adölesan ve yetişkinlik döneminde sağlıklı beslenmek yerine sürekli kısıtlayıcı diyetler yapıp kilo alıp-verme şeklinde devam ediyor.");
        bilgiler.add("Osmanlı hekimlerinin sağlıklı yaşam için tavsiye ettiği şeylerin başında az yemek geliyordu. Geç kahvaltı, erken akşam yemeği olmak üzere günde iki öğün beslenmeyi tavsiye eden doktorlar, tam doymadan insanları sofradan kalkmaya alıştırıyorlardı.");
        bilgiler.add("Yemeğin önemli bir parçası da sulu yemeklerin tercih edilmesiydi. Hazmı kolaylaştıran sulu yiyeceklerin tüketimi midenin sağlığı açısından sofrada önemli bir tercihti. Özellikle tam olgunlaşan meyvelerden yapılan hoşaf, bu nedenle sofraların önemli bir tercihiydi. Zira eski resimlerde sofra başında kaşıklı kişilerin görünümü bu durumu doğrular nitelikte.\n" +
                "\n" +
                "Bu yöntemleri uygulayan Osmanlı, az ve doğru yiyerek daha rahat bir yaşam sürdürmüş oluyorlardı.");
        bilgiler.add("Osmanlı'nın sağlıklı yaşam tavsiyeleri içerisinde spor da önemli bir yerde bulunuyor. Beyin dahil bütün organlara spor yaptırılmasını tavsiye eden hekimler, iç organların çalışması için at binmeyi tavsiye ediyor.\n" +
                "\n" +
                "Bütün iç organları ve vücudu çalıştıran spor olan at binmek, zamanın en değerli sporları arasında görülürken, salıncağa, kayığa binmek, hem ferahlattığı hem de organları çalıştırdığı için yaşlılara bile uygulanması tavsiye edilirdi.");
        bilgiler.add("Osmanlı tıbbında uyku da sağlıklı yaşamın önemli parçalarından biri. Sadece dinlenmek için uygulanmayan uyku yiyeceklerin hazmedilmesi için de önemli bir araçtı");
        bilgiler.add("O zarar görmüş bölgenin üzerine iyice olgunlaşmış bir muzun birkaç dilimini koyun ve bir bandajla kapatın.Muzun içindeki enzimler ciltteki yabancı maddenin çıkmasını kolaylaştıracaktır.");
        Random r = new Random();
        int sayi = r.nextInt(29);
        txBilgi.setText(bilgiler.get(sayi));

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.ana_sayfa:
                        Intent i = new Intent(claimActivity.this, girisActivity.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        break;
                    case R.id.search:
                        Intent i2 = new Intent(claimActivity.this, searchActivity.class);
                        startActivity(i2);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        break;
                    case R.id.sosyal_medya:
                        Intent i3 = new Intent(claimActivity.this, sosyalMedyaActivity.class);
                        startActivity(i3);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        break;
                    case R.id.uygulama_hakkında:
                        Intent i4 = new Intent(claimActivity.this, abouthAppActivity.class);
                        startActivity(i4);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        break;

                }
                return true;
            }
        });
        Menu menu = bottomNavigationView.getMenu();
        menu.findItem(R.id.istekleriniz).setIcon(R.drawable.ic_email_bold);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.kayitli_ilaclar:
                Intent intent = new Intent(claimActivity.this, ilacListeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.kayitli_alarmlar:
                Intent intent4 = new Intent(claimActivity.this, alarmListActivity.class);
                startActivity(intent4);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.gunu_bitiyor:
                Intent intent5 = new Intent(claimActivity.this, ilacGunuBitiyorActivity.class);
                startActivity(intent5);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.olcumler:
                Intent intent9 = new Intent(claimActivity.this, olcumKaydetActivity.class);
                startActivity(intent9);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.randevu:
                Intent intent6 = new Intent(claimActivity.this, HastaneRandevuActivity.class);
                startActivity(intent6);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.alarm_ayarla:
                Intent intent2 = new Intent(claimActivity.this, MainActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.ilac_kaydet:
                Intent intent3 = new Intent(claimActivity.this, ilacKaydetActivity.class);
                startActivity(intent3);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.acil_numara:
                Intent intent7 = new Intent(claimActivity.this, acilNumberActivity.class);
                startActivity(intent7);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.randevu_list:
                Intent intent8 = new Intent(claimActivity.this, randevuListActivity.class);
                startActivity(intent8);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.right_top_button, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_name) {
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE:
                            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.benim.ilacsaati.uygulamam");
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            break;
                        case DialogInterface.BUTTON_NEGATIVE:
                            break;

                    }
                }
            };
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(claimActivity.this);
            builder.setMessage(R.string.app_star).setPositiveButton(R.string.yes_text, dialogClickListener).setNegativeButton(R.string.no_text, dialogClickListener).show();
        }
        if(id==android.R.id.home && !acikmi){
            acikmi=true;
            drawerLayout.openDrawer(GravityCompat.START);
        }
        else if(id==android.R.id.home && acikmi){
            acikmi=false;
            drawerLayout.closeDrawers();
        }
        return super.onOptionsItemSelected(item);
    }
}