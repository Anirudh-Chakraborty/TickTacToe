package com.test.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
// 0 = X
// 1 = O
    boolean gameActive = true;
    int activePlayer = 0;
    int gameState [] = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    //State meaning
    //  0 = x = C
    //  1 = O = D
    //  2 = Blank
    int [][] winPositions = {{0,1,2},{3,4,5},{6,7,8},//horizontal
                             {0,3,6},{1,4,7},{2,5,8},//vertical
                                {0,4,8},{2,4,6}};//diagonal

    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if (!gameActive){
            //reset the game
            gameReset(view);
        }
        //playing the game
        if (gameState[tappedImage] == 2 && gameActive) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.d);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("Turn to play : X");
            } else {
                img.setImageResource(R.drawable.c);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("Turn to play : O");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }


        // for checking if anyone has won
        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[1]] != 2) {
                //someone has won the game
                //find the winner
                String winnerStr;
                gameActive = false;
                if (gameState[winPosition [0]] == 0){
                    winnerStr = "O has Won";
                    System.out.println(winnerStr);
                } else{
                    winnerStr = "X has Won";
                    System.out.println(winnerStr);
                } // Update the Status bar for winner
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
            }
            //for draw
            else if (gameState[0]!=2&&
                gameState[1]!=2&&
                gameState[2]!=2&&
                gameState[3]!=2&&
                gameState[4]!=2&&
                gameState[5]!=2&&
                gameState[6]!=2&&
                gameState[7]!=2&&
                gameState[8]!=2)
            {
                gameActive = false;
                TextView status = findViewById(R.id.status);
                status.setText("Game is Over");
            }
        }
    }

    public void gameReset(View view){
        gameActive = true;
        activePlayer= 0;
        for(int i = 0; i<gameState.length; i++){
            gameState [i] = 2;
        }
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView9)).setImageResource(0);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public int[] getGameState() {
        return gameState;
    }
}