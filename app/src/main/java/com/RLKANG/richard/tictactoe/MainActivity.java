package com.RLKANG.richard.tictactoe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Global variables to keep up with preferences
    private static final int SETTINGS = 1;
    public static boolean filled[][] = new boolean[3][3];
    public static Button buttons[][]= new Button[3][3];
    public static boolean won = false;
    public static boolean turn = true;
    public static boolean mode = true;
    public static boolean challenge = false;
    public static int count = 0;
    public static int level = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PreferenceManager.setDefaultValues(this, R.xml.user_settings, false);
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        update(sharedPrefs, true);

        Button button1 = (Button) findViewById(R.id.button1);
        buttons[0][0] = button1;
        Button button2 = (Button) findViewById(R.id.button2);
        buttons[0][1] = button2;
        Button button3 = (Button) findViewById(R.id.button3);
        buttons[0][2] = button3;
        Button button4 = (Button) findViewById(R.id.button4);
        buttons[1][0] = button4;
        Button button5 = (Button) findViewById(R.id.button5);
        buttons[1][1] = button5;
        Button button6 = (Button) findViewById(R.id.button6);
        buttons[1][2] = button6;
        Button button7 = (Button) findViewById(R.id.button7);
        buttons[2][0] = button7;
        Button button8 = (Button) findViewById(R.id.button8);
        buttons[2][1] = button8;
        Button button9 = (Button) findViewById(R.id.button9);
        buttons[2][2] = button9;

        Reset();
        if (challenge && !mode) {
            ComputerMode("X");
        }
    }

    public void Settings(View view) {
        Intent i = new Intent(getApplicationContext(), UserSettingActivity.class);
        startActivityForResult(i, SETTINGS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SETTINGS) {
            updateUserSettings();
        }
    }

    public void updateUserSettings() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        update(sharedPrefs, false);
    }

    //Update home screen with preferences
    public void update(SharedPreferences sharedPrefs, boolean init) {
        TextView displayMode = (TextView) findViewById(R.id.displayMode);
        TextView displayLevel = (TextView) findViewById(R.id.displayLevel);
        assert (displayMode != null);
        assert (displayLevel != null);
        boolean made_change= false;

        //Find current mode
        int cur_mode = Integer.parseInt(sharedPrefs.getString("prefUpdateMode", "1"));
        if (!init && ((cur_mode == 1 && !mode) || (cur_mode == 2 && mode))) {
            Reset();
            made_change= true;
        }
        if (cur_mode == 1) {
            mode = true;
            displayMode.setText(R.string.mode1);
            displayLevel.setText(R.string.empty);
        } else {
            mode = false;
            displayMode.setText(R.string.mode2);
        }

        //Find current level
        int cur_level = Integer.parseInt(sharedPrefs.getString("prefUpdateLevel", "1"));
        if ((!init && !mode) && ((cur_level - 1) != level)) {
            Reset();
            made_change= true;
        }
        if (cur_level == 1) {
            level = 0;
            if (!mode) {
                displayLevel.setText(R.string.easy);
            }
        } else if (cur_level == 2) {
            level = 1;
            if (!mode) {
                displayLevel.setText(R.string.medium);
            }
        } else {
            level = 2;
            if (!mode) {
                displayLevel.setText(R.string.hard);
            }
        }

        //Find current challenge mode
        int cur_challenge = Integer.parseInt(sharedPrefs.getString("prefUpdateChallenge", "1"));
        if ((!init && !mode) && ((cur_challenge == 1 && challenge) || (cur_challenge == 2 && !challenge))) {
            Reset();
            made_change= true;
        }
        if (cur_challenge == 1) {
            challenge = false;
        } else {
            challenge = true;
        }

        //Move the Computer (first move)
        if ((init || made_change) && (challenge && !mode)) {
            ComputerMode("X");
        }
    }

    //Updates the board when a button is clicked/marked
    public void UpdateState(View view) {
        if (mode) {
            //If Player vs. Player
            HumanMode(view);
        } else if (!challenge) {
            //If Player vs. Computer (challenge mode off)
            HumanMode(view);
            ComputerMode("O");
            turn = !turn;
        } else {
            //If Player vs. Computer (challenge mode on)
            turn = !turn;
            HumanMode(view);
            ComputerMode("X");
        }
    }

    //Player makes a move
    public void HumanMode(View view) {
        if (!won && count < 9) {
            for (int i=0; i<3; i++) {
                for (int j=0; j<3; j++) {
                    if (view==buttons[i][j]) {
                        if (turn) {
                            buttons[i][j].setText("X");
                            turn = false;
                        } else {
                            buttons[i][j].setText("O");
                            turn = true;
                        }
                        filled[i][j] = true;
                        ++count;
                        buttons[i][j].setClickable(false);
                    }
                }
            }
        }

        if (checkWin("X") || checkWin("O")) {
            TextView displayWin = (TextView) findViewById(R.id.displayWin);
            assert (displayWin != null);
            if (checkWin("X")) {
                displayWin.setText(R.string.message1);
            } else {
                displayWin.setText(R.string.message2);
            }
            won = true;
        } else if (count == 9) {
            TextView displayWin = (TextView) findViewById(R.id.displayWin);
            assert (displayWin != null);
            displayWin.setText(R.string.message3);
        }

        if (won || count == 9) {
            for (int i=0; i<3; i++) {
                for (int j=0; j<3; j++) {
                    if (!filled[i][j]) {
                        buttons[i][j].setClickable(false);
                    }
                }
            }
        }
    }

    //Computer makes a move
    public void ComputerMode(String s) {
        String opp;
        if (s.equals("X")) {
            opp = "O";
        } else {
            opp = "X";
        }

        if (count==9) { return; }

        if (checkWin(opp)) {
            TextView displayWin = (TextView) findViewById(R.id.displayWin);
            assert (displayWin != null);
            displayWin.setText(R.string.message4);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j].setClickable(false);
                }
            }
        } else {
            boolean made_move = false;

            if (level == 0 || level == 1) {
                //Win the game if possible
                if (level == 1) {
                    made_move = win(filled, buttons, s);
                }

                //Block the opponent from winning if possible
                if (!made_move && (level == 1)) {
                    made_move = blockWin(filled, buttons, s, opp);
                }

                //Make a fork if possible
            /*if (!made_move && level == 2) {
                made_move = fork(filled, buttons, s);
            }*/

                //Block a fork if possible
            /*if (!made_move && level == 2) {
                made_move = blockFork(filled, buttons, s, opp);
            }*/

                //Update count if made_move is true
                if (made_move) {
                    ++count;
                }

                //Take centre square if possible
                if (!made_move) {
                    if (!filled[1][1]) {
                        buttons[1][1].setText(s);
                        filled[1][1] = true;
                        ++count;
                        buttons[1][1].setClickable(false);
                        made_move = true;
                    }
                }

                //Take empty corner
                if (!made_move && (level == 0)) {
                    if (!filled[0][0]) {
                        buttons[0][0].setText(s);
                        filled[0][0] = true;
                        ++count;
                        buttons[0][0].setClickable(false);
                        made_move = true;
                    } else if (!filled[0][2]) {
                        buttons[0][2].setText(s);
                        filled[0][2] = true;
                        ++count;
                        buttons[0][2].setClickable(false);
                        made_move = true;
                    } else if (!filled[2][0]) {
                        buttons[2][0].setText(s);
                        filled[2][0] = true;
                        ++count;
                        buttons[2][0].setClickable(false);
                        made_move = true;
                    } else if (!filled[2][2]) {
                        buttons[2][2].setText(s);
                        filled[2][2] = true;
                        ++count;
                        buttons[2][2].setClickable(false);
                        made_move = true;
                    }
                }

                //Take empty edge
                if (!made_move) {
                    if (!filled[0][1]) {
                        buttons[0][1].setText(s);
                        filled[0][1] = true;
                        ++count;
                        buttons[0][1].setClickable(false);
                        made_move = true;
                    } else if (!filled[1][0]) {
                        buttons[1][0].setText(s);
                        filled[1][0] = true;
                        ++count;
                        buttons[1][0].setClickable(false);
                        made_move = true;
                    } else if (!filled[1][2]) {
                        buttons[1][2].setText(s);
                        filled[1][2] = true;
                        ++count;
                        buttons[1][2].setClickable(false);
                        made_move = true;
                    } else if (!filled[2][1]) {
                        buttons[2][1].setText(s);
                        filled[2][1] = true;
                        ++count;
                        buttons[2][1].setClickable(false);
                        made_move = true;
                    }
                }

                //Take next available spot
                boolean finished = false;
                if (!made_move) {
                    for (int i = 0; i < 3 && !finished; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (!filled[i][j]) {
                                buttons[i][j].setText(s);
                                filled[i][j] = true;
                                ++count;
                                buttons[i][j].setClickable(false);
                                finished = true;
                                break;
                            }
                        }
                    }
                }
            } else {
                //Hard level
                if (!filled[1][1]) {
                    buttons[1][1].setText(s);
                    filled[1][1] = true;
                    ++count;
                    buttons[1][1].setClickable(false);
                }
                else {
                    int[] next_move = move();
                    buttons[next_move[0]][next_move[1]].setText(s);
                    filled[next_move[0]][next_move[1]] = true;
                    ++count;
                    buttons[next_move[0]][next_move[1]].setClickable(false);
                }
            }
        }

        if (checkWin(s)) {
            TextView displayWin = (TextView) findViewById(R.id.displayWin);
            assert (displayWin != null);
            displayWin.setText(R.string.message5);
            for (int a = 0; a < 3; a++) {
                for (int b = 0; b < 3; b++) {
                    buttons[a][b].setClickable(false);
                }
            }
        } else if (count == 9) {
            TextView displayWin = (TextView) findViewById(R.id.displayWin);
            assert (displayWin != null);
            displayWin.setText(R.string.message3);
        }
    }

    //Win the game if possible
    public boolean win(boolean[][] filled, Button[][] buttons, String s) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!filled[i][j]) {
                    buttons[i][j].setText(s);
                    if (checkWin(s)) {
                        filled[i][j] = true;
                        buttons[i][j].setText(s);
                        buttons[i][j].setClickable(false);
                        return true;
                    } else {
                        buttons[i][j].setText("");
                    }
                }
            }
        }
        return false;
    }

    //Block the opponent from winning if possible
    public boolean blockWin(boolean[][] filled, Button[][] buttons, String s, String opp) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!filled[i][j]) {
                    buttons[i][j].setText(opp);
                    if (checkWin(opp)) {
                        filled[i][j] = true;
                        buttons[i][j].setText(s);
                        buttons[i][j].setClickable(false);
                        return true;
                    } else {
                        buttons[i][j].setText("");
                    }
                }
            }
        }
        return false;
    }

    //Make a fork if possible
    /*public boolean fork(boolean[][] filled, Button[][] buttons, String s) {
        int twos_count = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!filled[i][j]) {
                    buttons[i][j].setText(s);
                    filled[i][j] = true;

                    for (int a = 0; a < 3; a++) {
                        for (int b = 0; b < 3; b++) {
                            if (!filled[a][b]) {
                                buttons[a][b].setText(s);
                                if (checkWin(s)) {
                                    ++twos_count;
                                }
                                buttons[a][b].setText("");
                            }
                        }
                    }

                    if (twos_count >= 2) {
                        //buttons[i][j].setText(s);
                        buttons[i][j].setClickable(false);
                        return true;
                    }
                    buttons[i][j].setText("");
                    filled[i][j] = false;
                    twos_count = 0;
                }
            }
        }
        return false;
    }*/

    //Block a fork if possible
    /*public boolean blockFork(boolean[][] filled, Button[][] buttons, String s, String opp) {
        int twos_count = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!filled[i][j]) {
                    buttons[i][j].setText(opp);
                    filled[i][j] = true;

                    for (int a = 0; a < 3; a++) {
                        for (int b = 0; b < 3; b++) {
                            if (!filled[a][b]) {
                                buttons[a][b].setText(opp);
                                if (checkWin(opp)) {
                                    ++twos_count;
                                }
                                buttons[a][b].setText("");
                            }
                        }
                    }

                    if (twos_count >= 2) {
                        buttons[i][j].setText(s);
                        buttons[i][j].setClickable(false);
                        return true;
                    }
                    buttons[i][j].setText("");
                    filled[i][j] = false;
                    twos_count = 0;
                }
            }
        }
        return false;
    }*/

    /* *** MINIMAX ALGORITHM FOR UNBEATABLE AI (HARD LEVEL) *** */

    //"Main method" to get the row number and column number of best move
    public int[] move() {
        int[] result;
        if (challenge) {
            result= minimax(2, "X", Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        else {
            result= minimax(2, "O", Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        return new int[] {result[1], result[2]};
    }

    //Actual minimax algorithm, with alpha-beta pruning
    public int[] minimax (int depth, String cur, int alpha, int beta) {

        String player= (challenge) ? "O" : "X";
        String computer= (challenge) ? "X" : "O";

        List<int[]> next_Moves = generateMoves();
        int score, bestRow= -1, bestCol= -1;

        if (next_Moves.isEmpty() || depth==0) {
            score = evaluate();
            return new int[] {score, bestRow, bestCol};
        }
        else {
            for (int[] move: next_Moves) {
                buttons[move[0]][move[1]].setText(cur);
                filled[move[0]][move[1]]= true;
                if (cur.equals(computer)) {
                    score= minimax(depth-1, player, alpha, beta)[0];
                    if (score>alpha) {
                        alpha= score;
                        bestRow= move[0];
                        bestCol= move[1];
                    }
                }
                else {
                    score= minimax(depth-1, computer, alpha, beta)[0];
                    if (score<beta) {
                        beta= score;
                        bestRow= move[0];
                        bestCol= move[1];
                    }
                }
                buttons[move[0]][move[1]].setText("");
                filled[move[0]][move[1]]= false;
                if (alpha >= beta) break;
            }
        }
        return new int[] {(cur.equals(computer)) ? alpha : beta, bestRow, bestCol};
    }

    //Generates a list of possible moves
    public List <int[]> generateMoves() {
        List<int[]> next_Moves = new ArrayList<>();
        if (checkWin("X") || checkWin("O")) {
            return next_Moves;
        }
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                if (!filled[i][j]) {
                    next_Moves.add(new int[] {i,j});
                }
            }
        }
        return next_Moves;
    }

    //Heuristic evaluation function for the board
    public int evaluate() {
        int score = 0;
        score+= evaluateLine(0, 0, 0, 1, 0, 2) + evaluateLine(1, 0, 1, 1, 1, 2) + evaluateLine(2, 0, 2, 1, 2, 2)
                + evaluateLine(0, 0, 1, 0, 2, 0) + evaluateLine(0, 1, 1, 1, 2, 1) + evaluateLine(0, 2, 1, 2, 2, 2)
                + evaluateLine(0, 0, 1, 1, 2, 2) + evaluateLine(0, 2, 1, 1, 2, 0);
        return score;
    }

    //Evaluates each line and returns a "score" of each line
    public int evaluateLine(int row1, int col1, int row2, int col2, int row3, int col3) {
        String player= (challenge) ? "O" : "X";
        String computer= (challenge) ? "X" : "O";
        int score= 0;

        if (buttons[row1][col1].getText().equals(computer)) {
            score= 1;
        } else if (buttons[row1][col1].getText().equals(player)) {
            score= -1;
        }

        if (buttons[row2][col2].getText().equals(computer)) {
            if (score==1) {
                score= 10;
            } else if (score == -1) {
                return 0;
            } else {
                score= 1;
            }
        } else if (buttons[row2][col2].getText().equals(player)) {
            if (score==-1) {
                score= -10;
            } else if (score==1) {
                return 0;
            } else {
                score= -1;
            }
        }

        if (buttons[row3][col3].getText().equals(computer)) {
            if (score>0) {
                score*= 10;
            } else if (score<0) {
                return 0;
            } else {
                score= 1;
            }
        } else if (buttons[row3][col3].getText().equals(player)) {
            if (score<0) {
                score*= 10;
            } else if (score>1) {
                return 0;
            } else {
                score= -1;
            }
        }
        return score;
    }

    /* *** END OF MINIMAX ALGORITHM *** */

    //New game
    public void NewGame(View view) {
        Reset();
        if (challenge && !mode) {
            ComputerMode("X");
        }
    }

    //Reset game board
    public void Reset() {
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                buttons[i][j].setText(R.string.empty);
                buttons[i][j].setClickable(true);
            }
        }

        TextView displayWin = (TextView) findViewById(R.id.displayWin);
        assert (displayWin != null);
        displayWin.setText(R.string.empty);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                filled[i][j] = false;
            }
        }
        won = false;
        turn = true;
        count = 0;
    }

    //Check if a user has won
    public boolean checkWin(String s) {
        if (buttons[0][0].getText().equals(s) && buttons[0][1].getText().equals(s) && buttons[0][2].getText().equals(s)) {
            return true;
        } else if (buttons[1][0].getText().equals(s) && buttons[1][1].getText().equals(s) && buttons[1][2].getText().equals(s)) {
            return true;
        } else if (buttons[2][0].getText().equals(s) && buttons[2][1].getText().equals(s) && buttons[2][2].getText().equals(s)) {
            return true;
        } else if (buttons[0][0].getText().equals(s) && buttons[1][0].getText().equals(s) && buttons[2][0].getText().equals(s)) {
            return true;
        } else if (buttons[0][1].getText().equals(s) && buttons[1][1].getText().equals(s) && buttons[2][1].getText().equals(s)) {
            return true;
        } else if (buttons[0][2].getText().equals(s) && buttons[1][2].getText().equals(s) && buttons[2][2].getText().equals(s)) {
            return true;
        } else if (buttons[0][0].getText().equals(s) && buttons[1][1].getText().equals(s) && buttons[2][2].getText().equals(s)) {
            return true;
        } else if (buttons[0][2].getText().equals(s) && buttons[1][1].getText().equals(s) && buttons[2][0].getText().equals(s)) {
            return true;
        }
        return false;
    }

}
