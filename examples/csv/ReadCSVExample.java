import java.io.*;

public class ReadCSVExample
{
    public static void main(String args[]) throws FileNotFoundException, IOException {
        FileInputStream fstream = new FileInputStream("data.csv");
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
        int mujeres, varones;

        while ((line = br.readLine()) != null)   {
            if(line.startsWith("#"))
                continue;

            String data[] = line.split(",", 9);

            if(data[2].matches("SALTA|CORDOBA"))
                continue;

            mujeres = Integer.parseInt(data[3]);
            varones = Integer.parseInt(data[4]);

            if(mujeres > varones) {
                System.out.println(data[2] + ", " + data[1] + ", " + data[0]);
            }
        }

        in.close();
    }
}
