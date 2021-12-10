package com.example.shareadrop;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.ComponentActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;


import java.util.Observable;
import java.util.Vector;
import java.util.concurrent.Semaphore;

public class SliderAdapter extends PagerAdapter{

    Context context;
    LayoutInflater layoutInflater;



    public SliderAdapter(Context context){
        this.context = context;

    }
    public  int[] slide_images = {

            R.drawable.mother1,
            R.drawable.mom2,
            R.drawable.milk,
            R.drawable.milkbank,
            R.drawable.milkbank,
            R.drawable.tips,
            R.drawable.feed,
            R.drawable.mothers1,
            R.drawable.pump,
            R.drawable.milkbag1,
            R.drawable.ref,
            R.drawable.thaw,
            R.drawable.container,


    };

    public  String[] video = {

            "R9mYuYH1t8M",
            "vXOnJ0DT_xI",
            "JjFzjrZRBM",
            "Df9tg25i5V8",
            "3wwzUSC5RU4",
            "R9mYuYH1t8M",
            "vXOnJ0DT_xI",
            "JjFzjrZRBM",
            "Df9tg25i5V8",
            "3wwzUSC5RU4",
            "R9mYuYH1t8M",
            "vXOnJ0DT_xI",
            "JjFzjrZRBM"





    };
    public  String[] slide_headings = {

            "Healthy Tips ",
            "BreastMilk Donation Process ",
            "BreastMilk ",
            "Milk Bank ",
            "Milk Bank Process BreastMilk",
            "Breastfeeding Techniques",
            "Correct Position During Breastfeeding ",
            "Who Can Be A Possible Milk Donor?",
            "How To Pump Breastmilk?",
            "Storage of Breast Milk After Expressing",
            "Storage Tips",
            "Safe Thawing of Breast Milk",
            "Feeding Expressed BreastMilk"

    };

    public  String[] slide_description = {
            "The system also displays information about how the breast milk donation process works, facts and knowledge of why breast milk is important to babies, including promotional information about milk banks and who the potential donors are. The system also includes information on how to properly store breast milk, express milk by hand or with a pump, tips on how to freeze milk and handle the milk bank. For example, keep milk at room temperature, refrigerate it, put the milk in the freezer, and use cold compresses.\n\n ",
            "Step 1: Complete Donor Screening Form \n\n" +
                    "Step 2: Blood Test (HIV, SYPHILIS, HEPATITIS B) \n\n" +
                    "Step 3: Approval for Donation of Human Milk\n\n",
            "Why are breastfeeding and breast milk important?\n\n" +

                    "Breastfeeding is one of the most efficacious ways to ensure the health and survival of every child. However, almost 2 out of 3 babies are not exclusively breastfed for the recommended 6 months, a rate that has not improved in 2 decades. Breast milk is an exemplary food for babies. It's safe, clean, and contains antibodies that help protect against many common childhood illnesses. Breast milk provides all the energy and nutrients your baby needs for the first 6 months of life. Breastfed children do better on intelligence tests, are less likely to be overweight or obese, and are less prone to diabetes later in life. Women who breastfeed also have a lower chance of having breast and ovarian cancer.\n\n" +
                    "Useful link: "+ R.string.BreastMilk +"\n\n",
            "How does Human milk bank work?\n\n" + "Breastmilk banks depend on donations of breastmilk from breastfeeding women. A nurse verifies, advises and informs interested mothers about the procedure and the purpose of donating breast milk. \n\n" +
                    "Milk banks are managed and supervised by a midwife, pediatrician or senior scientist. Employees who handle milk are often kindergarten teachers, but some milk banks train people of different backgrounds in milk processing.\n\n" +
                    "Milk banks ensure that all employees who handle donor breast milk are trained on how to use milk safely.  To obtain breast milk from the milk bank, beneficiaries must provide a prescription, complete the required forms, and pay a minimum fee to keep the milk bank running.\n\n",
            "Breast milk is collected from dispensers using manual methods or breast pumps, with the help of the breast milk bank staff. The collected raw breast milk is stored in a freezer. Each container is labeled with complete information such as the name of the donor, the date and time of expression, and the amount of breast milk expressed. To prepare breast milk for pasteurization, it is first thawed in the refrigerator overnight. The breast milk from multiple donors is then collected under a laminar flow hood, a ventilation device that controls the circulation of filtered air to prevent contamination while the milk is transferred to new canisters. Each batch of breast milk is pasteurized at 62.5°C for 30 minutes. Pasteurization kills bacteria, viruses and other microorganisms and extends the shelf life of breast milk. Samples from each batch are sent to the hospital lab for microbiological testing before and after pasteurization to ensure safety and quality. The pasteurized milk is then stored in the freezer at 20°C. Frozen pasteurized breast milk is placed in a cooler with ice or gel packs to maintain temperature during transportation. Once thawed, the milk should be consumed within 24 hours. \n\n",
            " Hold the baby close enough and support the baby's neck and shoulder \n\n" +
                    "Help the baby get enough milk by placing the baby's lower lip near the base of the areola \n\n" +
                    "Offer both breasts one at a time at each breastfeeding period \n\n" +
                    "Begin to breastfeed with the breast that the baby used previously\n\n" +
                    "Useful links: https://www.facebook.com/Department-of-Pediatrics-JBLMGH-Community-108280534812263/photos/pcb.129278032712513/129277956045854\n\n",
            " Lying with parallel bodies. \n\n" +
                    "Hold your baby on your own lap and support him with the same arm as her breast. \n\n" +
                    "Hold your baby on your own lap using the arm opposite the breast that is feeding him. \n\n" +
                    "Hold your baby in any combination. If you have twins and wants to feed them together.\n\n" +
                    "Useful links https://www.facebook.com/Department-of-Pediatrics-JBLMGH-Community-108280534812263/photos/pcb.129278032712513/129277866045863\n\n",
            "Any breastfeeding woman who can donate her healthy expressed breast milk. Breast milk donors must be willing to undergo oral and written examinations. If eligible, he or she must be willing to undergo a thorough physical examination and serological tests.\n\n" +
                    "Breast milk donors must be willing to accept and follow basic instructions on the pumping, collection, storage and transport of breast milk. \n\n" +
                    "Donors must: \n\n" +
                    "Eat and feel good \n\n" +
                    "Not a smoker or secondhand smoker \n\n" +
                    "Never receive a blood transfusion \n\n" +
                    "Never receive drugs or human tissue  \n\n" +
                    "Do not drink too much caffeine \n\n" +
                    "Do not use illegal drugs \n\n" +
                    "Stay within reasonable limits if you drink alcohol\n\n" +
                    "Usefullinks:  https://www.humanitarianresponse.info/sites/www.humanitarianresponse.info/files/documents/files/FINAL%20DRAFT_PNCHMB%20GUIDELINES_June%2029%20Meeting%20Edited%202%20%281%29.pdf\n\n\n",
            "The pumping procedure is very easy to follow. If a mother uses a manual pump, it is  a bit more work than using an electric pump, but the plan remains the same (although the time varies slightly, from ten to twenty  minutes). You do not need to sit down and express your milk until the last  drop has been squeezed out. However, it is important to express for 15 minutes even after the milk has stopped flowing. The pumping stimulates the breasts to maintain the milk supply.\n\n" +
                    "• Read the instructions for use and cleaning of the pump. Sanitize your hands before using the pump. \n\n" +
                    "• Center the nipple in the flanges, the conical parts that go to the breast. Lean forward slightly and turn on the pump. \n\n" +
                    "• Keep the pump at low speed and low suction power. Many mothers or women find this advice confusing because they assume that high speed and high suction will get the most milk out the fastest, but this couldn't be further from the truth. \n\n" +
                    "• Simulate what the baby is doing or what can decrease milk production. The baby's suck is slow and low, not fast and high, and it is too jarring for the mother's body to pump in such an intense environment.\n\n" +
                    "• Pump for 7 minutes. At first you may not see anything coming out  and this is normal. A few minutes after pumping, you will experience a \"tear drop\", where the milk begins to flow. \n\n" +
                    "• Stop the breast pump for 1 minute and massage the breast,  down from the armpit to the nipple, all around. This gives the breasts a break and allows them to reset on their own, in a sense. \n\n" +
                    "• Pump for another 7  minutes and then store the milk.\n\n" +
                    "Useful links: https://www.verywellfamily.com/using-a-breast-pump-431744\n\n",
            " • Use a breast milk storage bag or clean food container to store expressed breast milk. Make sure the container is made of glass or plastic and has a tight-fitting lid. \n\n" +
                    "• Avoid bottles with the recycling symbol number 7, which indicates that the container may be plastic containing BPA. \n\n" +
                    "• Never store breast milk in single-use bottle bags or plastic bags that are not intended for  breast milk storage. \n\n" +
                    "• Fresh or pumped milk can be stored: \n\n" +
                    "• At room temperature (77°F or colder) for up to 4 hours. \n\n" +
                    "• In  refrigerator for up to 4 days. \n\n" +
                    "\n\n" +
                    "• In the freezer for about 6 months is better; up to 12 months is acceptable. While freezing keeps food safe almost indefinitely, it's important to follow the recommended storage times  to get the best quality.\n\n" +
                    "Useful link: https://www.cdc.gov/breastfeeding/recommendations/handling_breastmilk.htm\n\n",
            "•Clearly label  breast milk with the date it was issued. \n\n" +
                    "• Do not store breast milk in the  refrigerator or freezer door. This will help protect the breast milk from sudden changes in temperature due to opening and closing the door. \n\n" +
                    "• If you  think you will not use fresh breast milk in 4 days, freeze it immediately. This will help  protect the quality of  breast milk. \n\n" +
                    "• When freezing breast milk: \n\n" +
                    "o Store in small quantities to avoid wasting breast milk that may not be used up. Save 2 to 4 ounces or the amount offered at one feeding. \n\n" +
                    "o Leave about an inch of space at the top of the container for the milk to expand as it freezes. \n\n" +
                    "• If you give breast milk to a child care provider, clearly label the container with the baby's name. Talk to your child care provider about  other requirements for labeling and storing breast milk.\n\n" +
                    "• Breast milk can be stored in an insulated cold box with frozen ice packs for up to 24 hours when traveling. Use the milk immediately at the destination, store it in the refrigerator or freeze it.\n\n" +
                    "Useful link: https://www.cdc.gov/breastfeeding/recommendations/handling_breastmilk.htm\n\n",
            "• Always liquefy the oldest breast milk first. Remember the first one in, the first one out. Over time, the quality of breast milk can decrease. \n\n" +
                    "• There are several ways to liquefy breast milk: \n\n" +
                    "o In the refrigerator overnight. \n\n" +
                    "o Put in a container of lukewarm or lukewarm water. \n\n" +
                    "o Under lukewarm running water. \n\n" +
                    "• Never liquefy or heat breast milk in the microwave. Microwaves can destroy nutrients in breast milk and create hot spots, which can burn a baby's mouth. \n\n" +
                    "• If you liquefy breast milk in the refrigerator, use it within 24 hours. Start counting the 24 hours your breast milk is completely thawed, not when you take it out of the freezer. \n\n" +
                    "• Once breast milk has been brought to room temperature or warmed, use  within 2 hours. \n\n" +
                    "• Never refreeze breast milk after it has been liquefied.\n\n" +
                    "Useful link: https://www.cdc.gov/breastfeeding/recommendations/handling_breastmilk.htm\n\n",
            "• It is not necessary to warm breast milk. It can be served at room temperature or cold. \n\n" +
                    "• If you want to heat breast milk, here are some tips: \n\n" +
                    "o Keep the container closed. \n\n" +
                    "o Place the closed container in a container of lukewarm water or keep it under warm, but not hot, running water for a few minutes. \n\n" +
                    "o Before feeding your baby, test the  temperature of the milk by placing a few drops on your wrist. \n\n" +
                    "o Do not heat breast milk directly on the stove or in the microwave. \n\n" +
                    "• Shake breast milk to mix up any fat that may have settled. \n\n" +
                    "• If your baby has not finished bottle feeding, use the leftover milk within 2 hours after the baby has finished feeding. After 2 hours, the leftover breast milk should be discarded\n\n" +
                    "Useful link: https://www.cdc.gov/breastfeeding/recommendations/handling_breastmilk.html\n\n"
    };


    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }


    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideimg = (ImageView) view.findViewById(R.id.slide_img);
        TextView slidehead = (TextView) view.findViewById(R.id.slide_heading);
        TextView slidedesc = (TextView) view.findViewById(R.id.slide_desc);


        slideimg.setImageResource(slide_images[position]);
        slidehead.setText(slide_headings[position]);
        slidedesc.setText(slide_description[position]);





        slideimg.setVisibility(View.GONE);

        container.addView(view);

        return view;
    }



    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((ConstraintLayout)object);

        layoutInflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);




    }

}
