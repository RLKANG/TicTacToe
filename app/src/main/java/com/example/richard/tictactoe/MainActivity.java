    package com.example.richard.tictactoe;

    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.RadioButton;
    import android.widget.RadioGroup;
    import android.widget.TextView;

    public class MainActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }

        public static boolean filled[][] = new boolean[3][3];
        public static boolean won = false;
        public static boolean turn = true;
        public static boolean mode = true;
        public static int count = 0;
        public static int level = 0;

        public void UpdateState(View view) {
            if (mode) {
                HumanMode(view);
            }
            else {
                HumanMode(view);
                ComputerMode(view);
                turn=!turn;
            }
        }

        public void RadioButtonClick (View view) {
            if (view.getId() == R.id.radio_easy) {
                level=0;
            }
            else if (view.getId() == R.id.radio_medium) {
                level=1;
            }
            else {
                level=2;
            }
            NewGame(view);
        }

        public void HumanMode (View view) {
            if (!won && count<9) {
                if (view.getId() == R.id.button1) {
                    Button button = (Button) findViewById(R.id.button1);
                    assert (button != null);
                    if (turn) {
                        button.setText("X");
                        turn = false;
                    } else {
                        button.setText("O");
                        turn = true;
                    }
                    filled[0][0] = true;
                    ++count;
                    button.setClickable(false);
                } else if (view.getId() == R.id.button2) {
                    Button button = (Button) findViewById(R.id.button2);
                    assert (button != null);
                    if (turn) {
                        button.setText("X");
                        turn = false;
                    } else {
                        button.setText("O");
                        turn = true;
                    }
                    filled[0][1] = true;
                    ++count;
                    button.setClickable(false);
                } else if (view.getId() == R.id.button3) {
                    Button button = (Button) findViewById(R.id.button3);
                    assert (button != null);
                    if (turn) {
                        button.setText("X");
                        turn = false;
                    } else {
                        button.setText("O");
                        turn = true;
                    }
                    filled[0][2] = true;
                    ++count;
                    button.setClickable(false);
                } else if (view.getId() == R.id.button4) {
                    Button button = (Button) findViewById(R.id.button4);
                    assert (button != null);
                    if (turn) {
                        button.setText("X");
                        turn = false;
                    } else {
                        button.setText("O");
                        turn = true;
                    }
                    filled[1][0] = true;
                    ++count;
                    button.setClickable(false);
                } else if (view.getId() == R.id.button5) {
                    Button button = (Button) findViewById(R.id.button5);
                    assert (button != null);
                    if (turn) {
                        button.setText("X");
                        turn = false;
                    } else {
                        button.setText("O");
                        turn = true;
                    }
                    filled[1][1] = true;
                    ++count;
                    button.setClickable(false);
                } else if (view.getId() == R.id.button6) {
                    Button button = (Button) findViewById(R.id.button6);
                    assert (button != null);
                    if (turn) {
                        button.setText("X");
                        turn = false;
                    } else {
                        button.setText("O");
                        turn = true;
                    }
                    filled[1][2] = true;
                    ++count;
                    button.setClickable(false);
                } else if (view.getId() == R.id.button7) {
                    Button button = (Button) findViewById(R.id.button7);
                    assert (button != null);
                    if (turn) {
                        button.setText("X");
                        turn = false;
                    } else {
                        button.setText("O");
                        turn = true;
                    }
                    filled[2][0] = true;
                    ++count;
                    button.setClickable(false);
                } else if (view.getId() == R.id.button8) {
                    Button button = (Button) findViewById(R.id.button8);
                    assert (button != null);
                    if (turn) {
                        button.setText("X");
                        turn = false;
                    } else {
                        button.setText("O");
                        turn = true;
                    }
                    filled[2][1] = true;
                    ++count;
                    button.setClickable(false);
                } else if (view.getId() == R.id.button9) {
                    Button button = (Button) findViewById(R.id.button9);
                    assert (button != null);
                    if (turn) {
                        button.setText("X");
                        turn = false;
                    } else {
                        button.setText("O");
                        turn = true;
                    }
                    filled[2][2] = true;
                    ++count;
                    button.setClickable(false);
                }
            }
            if (checkWin("X") || checkWin("O")) {
                TextView tv = (TextView) findViewById(R.id.text1);
                assert(tv!=null);
                if (checkWin("X")) {
                    tv.setText(R.string.message1);
                } else {
                    tv.setText(R.string.message2);
                }
                won=true;
            }
            else if (count==9) {
                TextView tv = (TextView) findViewById(R.id.text1);
                assert(tv!=null);
                tv.setText(R.string.message3);
            }
        }

        public void ComputerMode (View view) {
            Button buttons[][] = new Button[3][3];
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

            assert (button1 != null);
            assert (button2 != null);
            assert (button3 != null);
            assert (button4 != null);
            assert (button5 != null);
            assert (button6 != null);
            assert (button7 != null);
            assert (button8 != null);
            assert (button9 != null);

            if (checkWin("X")) {
                TextView tv = (TextView) findViewById(R.id.text1);
                assert (tv != null);
                tv.setText(R.string.message4);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        buttons[i][j].setClickable(false);
                    }
                }
            } else {
                if (count == 1) {
                    if (button5.getText().equals("X")) {
                        button1.setText("O");
                        filled[0][0] = true;
                        ++count;
                        button1.setClickable(false);
                    } else {
                        button5.setText("O");
                        filled[1][1] = true;
                        ++count;
                        button5.setClickable(false);
                    }
                } else {
                    boolean made_move = false;
                    boolean finished = false;
                    if (level==2) {
                        for (int i = 0; i < 3 && !finished; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (!filled[i][j]) {
                                    buttons[i][j].setText("O");
                                    if (checkWin("O")) {
                                        TextView tv = (TextView) findViewById(R.id.text1);
                                        assert (tv != null);
                                        tv.setText(R.string.message5);
                                        filled[i][j] = true;
                                        ++count;
                                        buttons[i][j].setClickable(false);
                                        made_move = true;
                                        finished = true;
                                        for (int a = 0; a < 3; a++) {
                                            for (int b = 0; b < 3; b++) {
                                                buttons[a][b].setClickable(false);
                                            }
                                        }
                                        break;
                                    } else {
                                        buttons[i][j].setText("");
                                    }
                                }
                            }
                        }
                    }
                    if (level==1 || level==2) {
                        finished = false;
                        if (!made_move) {
                            for (int i = 0; i < 3 && !finished; i++) {
                                for (int j = 0; j < 3; j++) {
                                    if (!filled[i][j]) {
                                        buttons[i][j].setText("X");
                                        if (checkWin("X")) {
                                            buttons[i][j].setText("O");
                                            filled[i][j] = true;
                                            ++count;
                                            buttons[i][j].setClickable(false);
                                            made_move = true;
                                            finished = true;
                                            break;
                                        } else {
                                            buttons[i][j].setText("");
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (!made_move) {
                       if (!filled[0][1]) {
                           buttons[0][1].setText("O");
                           filled[0][1] = true;
                           ++count;
                           buttons[0][1].setClickable(false);
                           made_move=true;
                       }
                        else if (!filled[1][0]) {
                           buttons[1][0].setText("O");
                           filled[1][0] = true;
                           ++count;
                           buttons[1][0].setClickable(false);
                           made_move=true;
                       }
                       else if (!filled[1][2]) {
                           buttons[1][2].setText("O");
                           filled[1][2] = true;
                           ++count;
                           buttons[1][2].setClickable(false);
                           made_move=true;
                       }
                       else if (!filled[2][1]) {
                           buttons[2][1].setText("O");
                           filled[2][1] = true;
                           ++count;
                           buttons[2][1].setClickable(false);
                           made_move=true;
                       }
                    }

                    finished = false;
                    if (!made_move) {
                        for (int i = 0; i < 3 && !finished; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (!filled[i][j]) {
                                    buttons[i][j].setText("O");
                                    filled[i][j] = true;
                                    ++count;
                                    buttons[i][j].setClickable(false);
                                    finished = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        public void NewGame (View view) {
            Button button1 = (Button) findViewById(R.id.button1);
            assert(button1!=null);
            button1.setText("");
            button1.setClickable(true);
            Button button2 = (Button) findViewById(R.id.button2);
            assert(button2!=null);
            button2.setText("");
            button2.setClickable(true);
            Button button3 = (Button) findViewById(R.id.button3);
            assert(button3!=null);
            button3.setText("");
            button3.setClickable(true);
            Button button4 = (Button) findViewById(R.id.button4);
            assert(button4!=null);
            button4.setText("");
            button4.setClickable(true);
            Button button5 = (Button) findViewById(R.id.button5);
            assert(button5!=null);
            button5.setText("");
            button5.setClickable(true);
            Button button6 = (Button) findViewById(R.id.button6);
            assert(button6!=null);
            button6.setText("");
            button6.setClickable(true);
            Button button7 = (Button) findViewById(R.id.button7);
            assert(button7!=null);
            button7.setText("");
            button7.setClickable(true);
            Button button8 = (Button) findViewById(R.id.button8);
            assert(button8!=null);
            button8.setText("");
            button8.setClickable(true);
            Button button9 = (Button) findViewById(R.id.button9);
            assert(button9!=null);
            button9.setText("");
            button9.setClickable(true);

            TextView tv = (TextView) findViewById(R.id.text1);
            assert(tv!=null);
            tv.setText(R.string.empty);

            for (int i=0; i<3; i++) {
                for (int j=0; j<3; j++) {
                    filled[i][j]=false;
                }
            }
            won= false;
            turn= true;
            count=0;
        }

        public void ChangeMode (View view) {
            TextView tv= (TextView) findViewById(R.id.text2);
            assert(tv!=null);
            RadioGroup rg= (RadioGroup) findViewById(R.id.radio_buttons);
            assert(rg!=null);
            if (mode) {
                mode = false;
                tv.setText(R.string.mode2);
                /*RadioButton r_easy= (RadioButton) findViewById(R.id.radio_easy);
                r_easy.setChecked(true);*/
                rg.setVisibility(View.VISIBLE);
            }
            else {
                mode = true;
                tv.setText(R.string.mode1);
                rg.setVisibility(View.INVISIBLE);
            }
            NewGame(view);
        }

        public boolean checkWin (String s) {
            Button buttons[][]= new Button[3][3];
            Button button1 = (Button) findViewById(R.id.button1);
            buttons[0][0]= button1;
            Button button2 = (Button) findViewById(R.id.button2);
            buttons[0][1]= button2;
            Button button3 = (Button) findViewById(R.id.button3);
            buttons[0][2]= button3;
            Button button4 = (Button) findViewById(R.id.button4);
            buttons[1][0]= button4;
            Button button5 = (Button) findViewById(R.id.button5);
            buttons[1][1]= button5;
            Button button6 = (Button) findViewById(R.id.button6);
            buttons[1][2]= button6;
            Button button7 = (Button) findViewById(R.id.button7);
            buttons[2][0]= button7;
            Button button8 = (Button) findViewById(R.id.button8);
            buttons[2][1]= button8;
            Button button9 = (Button) findViewById(R.id.button9);
            buttons[2][2]= button9;

            assert (button1 != null);
            assert (button2 != null);
            assert (button3 != null);
            assert (button4 != null);
            assert (button5 != null);
            assert (button6 != null);
            assert (button7 != null);
            assert (button8 != null);
            assert (button9 != null);

            if (buttons[0][0].getText().equals(s) && buttons[0][1].getText().equals(s) && buttons[0][2].getText().equals(s)) {
                return true;
            }
            else if (buttons[1][0].getText().equals(s) && buttons[1][1].getText().equals(s) && buttons[1][2].getText().equals(s)) {
                return true;
            }
            else if (buttons[2][0].getText().equals(s) && buttons[2][1].getText().equals(s) && buttons[2][2].getText().equals(s)) {
                return true;
            }
            else if (buttons[0][0].getText().equals(s) && buttons[1][0].getText().equals(s) && buttons[2][0].getText().equals(s)) {
                return true;
            }
            else if (buttons[0][1].getText().equals(s) && buttons[1][1].getText().equals(s) && buttons[2][1].getText().equals(s)) {
                return true;
            }
            else if (buttons[0][2].getText().equals(s) && buttons[1][2].getText().equals(s) && buttons[2][2].getText().equals(s)) {
                return true;
            }
            else if (buttons[0][0].getText().equals(s) && buttons[1][1].getText().equals(s) && buttons[2][2].getText().equals(s)) {
                return true;
            }
            else if (buttons[0][2].getText().equals(s) && buttons[1][1].getText().equals(s) && buttons[2][0].getText().equals(s)) {
                return true;
            }
            return false;
        }
    }
