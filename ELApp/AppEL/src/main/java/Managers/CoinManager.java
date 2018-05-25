package Managers;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CoinManager {
    private String coinPath;
    private FileManager fileManager;

    private CoinManager(Context context) {
        fileManager = FileManager.getFileManager();
        coinPath = fileManager.getAppPath(context) + "coin.txt";
    }

    public static CoinManager getCoinManager(Context context) {
        return new CoinManager(context);
    }

    public int getCoin() {
        String s = readFromFile();
        return Integer.parseInt(s);
    }

    public void setCoin(String s) {
        writeFile(Integer.parseInt(s));
    }

    private String readFromFile() {
        File file = new File(coinPath);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "0";
    }

    private void writeFile(int coin) {
        File file = new File(coinPath);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(String.valueOf(coin));
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
