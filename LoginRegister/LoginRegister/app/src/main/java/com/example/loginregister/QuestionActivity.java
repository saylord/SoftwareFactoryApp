package com.example.loginregister;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView question, qCount, timer;
    private Button option1, option2, option3;
    private List<com.example.loginregister.Question> questionList;
    private int quesNum;
    private CountDownTimer countDown;
    private int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        question = findViewById(R.id.question);
        qCount = findViewById(R.id.quest_num);
        timer = findViewById(R.id.countdown);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);

        getQuestionsList();
        score = 0;
    }

    private void getQuestionsList() {
        questionList = new ArrayList<>();
        questionList.add(new com.example.loginregister.Question("How do you describe your mood lately?", "Good", "Worse than usual", "Very bad"));
        questionList.add(new com.example.loginregister.Question("How do you imagine your future?", "Everything will be fine, a lot of pleasant and interesting things are waiting for me", "I find it difficult to answer. It depends on how lucky you are", "Nothing good awaits me, then everything will only get worse"));
        questionList.add(new com.example.loginregister.Question("Do you consider yourself a lucky person?", "Yes, I'm often lucky", "Rather no than yes, I'm rarely lucky", "I'm a loser, I'm constantly unlucky"));
        questionList.add(new com.example.loginregister.Question("Are you haunted by guilt?", "No, I don't consider myself guilty", "I find it difficult to answer. Maybe I, like everyone else, made mistakes earlier", "I made a lot of mistakes that I have to pay for"));
        questionList.add(new com.example.loginregister.Question("In general, are you satisfied with the way you live?", "Yes", "Hard to say", "No"));

        questionList.add(new com.example.loginregister.Question("Are you disappointed in yourself and the people around you (friends, loved one, relatives)?", "No", "All people are different, I find it difficult to answer", "Yes. You can't trust anyone"));
        questionList.add(new com.example.loginregister.Question("Do you consider yourself worthy of happiness, well-being, high income?", "I really deserve all this", "I find it difficult to say. There is still a lot to work on", "I'm not good enough to get it all"));
        questionList.add(new com.example.loginregister.Question("How easily do you make decisions?", "As soon as there is a need to solve something, I do it", "I make really serious decisions right away, and I postpone not very serious ones for later", "I can't make up my mind for a long time, I often change my mind and delay with the final answer in the hope that everything will resolve itself"));
        questionList.add(new com.example.loginregister.Question("Do you enjoy communicating with people around you?", "Yes, I enjoy communicating with nice people", "I like to communicate only with some particularly pleasant interlocutors. And there are not so many of them", "I absolutely do not want to communicate. It tires me and annoys me"));
        questionList.add(new com.example.loginregister.Question("Do you like the way you look lately?", "Yes. I, as before, perceive my appearance", "I find it difficult to answer. There is sometimes dissatisfaction", "I began to look much worse than before"));

        questionList.add(new com.example.loginregister.Question("Have you been getting tired quickly lately?", "No. As before, I am full of strength and energy", "There are bad days, but there are not many of them", "Yes. Most of the time I have to overwork myself to fulfill my usual duties"));
        questionList.add(new com.example.loginregister.Question("Do you sleep well and fall asleep easily?", "Yes, it is so", "Sometimes I have problems with this", "Most often I can't sleep for a long time, I sleep badly, I get up hard, I don't get enough sleep, I feel restless and broken"));
        questionList.add(new com.example.loginregister.Question("Has your appetite changed?", "No, I eat as much as before", "I find it difficult to answer, it happens in different ways", "My appetite has clearly changed, I don't want to eat at all, or on the contrary, I am overcome by a constant feeling of hunger"));
        questionList.add(new com.example.loginregister.Question("Does your weight stay at the same level?", "Yes, it is", "Fluctuates within 5% of body weight", "Recently I have lost a lot of weight or gained weight"));
        questionList.add(new com.example.loginregister.Question("How much do you care about health and maintaining physical fitness?", "As before", "Slightly more or less than before", "I am very concerned about my health, I am afraid of getting sick and dying. But this state is sometimes replaced by complete indifference and the realization that I can't change anything"));

        questionList.add(new com.example.loginregister.Question("Have you changed your attitude to sex?", "No, I feel the same attraction as before", "Sometimes it seems to me that the desire has become less", "Yes, the attitude towards sex has changed, and its frequency and duration have significantly decreased"));
        setQuestion();
    }

    private void setQuestion() {
        timer.setText(String.valueOf(10));
        question.setText(questionList.get(0).getQuestion());
        option1.setText(questionList.get(0).getOptionA());
        option2.setText(questionList.get(0).getOptionB());
        option3.setText(questionList.get(0).getOptionC());

        qCount.setText(String.valueOf(1) + "/" + String.valueOf(questionList.size()));

        startTimer();
        quesNum = 0;
    }

    private void startTimer() {
        countDown = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished > 1000)
                    timer.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                changeQuestion();
            }
        };
        countDown.start();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.option1:
                score += 1;
                break;
            case R.id.option2:
                score += 2;
                break;
            case R.id.option3:
                score += 3;
                break;
            default:
        }
        countDown.cancel();
        checkAnswer(score, v);
    }

    private void checkAnswer(int score, View view) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    changeQuestion();
                                }
                            },
                2000);
    }

    private void changeQuestion(){
        if(quesNum < questionList.size() -1){
            quesNum++;
            playAnim(question, 0, 0);
            playAnim(option1, 0,1);
            playAnim(option2, 0,2);
            playAnim(option3, 0,3);

            qCount.setText(String.valueOf(quesNum+1) + "/" + String.valueOf(questionList.size()));
            timer.setText(String.valueOf(10));
            startTimer();
        }
        else{
            Intent intent = new Intent(QuestionActivity.this, com.example.loginregister.ScoreActivity.class);
            intent.putExtra("SCORE",String.valueOf(score)+ "/" + 48);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("Result", showResult(score));
            startActivity(intent);
        }
    }

    private String showResult(int totalScore) {
        if (totalScore >= 16 & totalScore <= 23) {
            return "This result indicates that you, like any other person, have a bad mood and bad days. But this does not affect the attitude to life in general, self-esteem and health. Based on the test results, you have no characteristic symptoms of the disease, and there is no reason to worry.";
        } else if (totalScore >= 24 & totalScore <= 35) {
            return "This result is a reason to think and take a closer look at your condition. You often arrive in a pessimistic mood, and perhaps you have problems with an objective perception of reality. If you have sleep and appetite disorders, then do not postpone them, but proceed to eliminate them. Timely preventive measures taken will help to avoid serious problems with mental and physical health.Learn how to overcome depression on your own.";
        } else
            return "This result is a cause for concern. Don't ignore your bad mood. Most of the answers are clear signs of a depressive state and a distorted perception of the surrounding reality. You see your life in gray tones and this is not just a bad mood or a black streak, but a sign of depression.";
    }

    private void playAnim(View view, final int value, int viewNum){
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500)
                .setStartDelay(100).setInterpolator(new DecelerateInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        if(value == 0){
                            switch(viewNum){
                                case 0:
                                    ((TextView)view).setText(questionList.get(quesNum).getQuestion());
                                    break;
                                case 1:
                                    ((Button)view).setText(questionList.get(quesNum).getOptionA());
                                    break;
                                case 2:
                                    ((Button)view).setText(questionList.get(quesNum).getOptionB());
                                    break;
                                case 3:
                                    ((Button)view).setText(questionList.get(quesNum).getOptionC());
                                    break;
                            }
                            if(viewNum != 0)
                                ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E99C03")));

                            playAnim(view, 1,viewNum);
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        countDown.cancel();
    }
}