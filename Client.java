package speechtotext.androidhive.info.texttospeech;

/**
 * Created by karis on 6/2/2017.
 */

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Client extends AsyncTask<Void, Void, String> {

    String dstAddress;
    int dstPort;
    String response = "";
    String threadExecProgress = "";
    String textResponse;
    TextView textResponseView;
    int typeOfAction;
    ArrayList<String> applianceList = new ArrayList<>();
    ArrayList<String> sensorList = new ArrayList<>();

    Client(String addr, int port, String textResponse, TextView textResponseView, int typeOfAction) {
        dstAddress = addr;
        dstPort = port;
        this.textResponse = textResponse;
        this.textResponseView = textResponseView;
        this.typeOfAction = typeOfAction;
    }
    Client(String addr, int port, TextView textResponseView, int typeOfAction) {
        dstAddress = addr;
        dstPort = port;
        this.typeOfAction = typeOfAction;
        this.textResponseView = textResponseView;
    }
    @Override
    protected String doInBackground(Void... arg0) {

        Socket socket = null;

        try {
            socket = new Socket(dstAddress, dstPort);
            DataOutputStream DOS = new DataOutputStream(socket.getOutputStream());
            DOS.writeInt(typeOfAction);
            if (typeOfAction== 0) { //voice command
                DOS.writeUTF(String.valueOf(textResponse));
                DataInputStream DIS = new DataInputStream(socket.getInputStream());
                response = DIS.readUTF();
            }
            else if(typeOfAction ==1) { // get all connected devices and sensors
                ObjectInputStream OIS = new ObjectInputStream(socket.getInputStream());
                try {
                    Object list1 = OIS.readObject();
                    Object list2 = OIS.readObject();
                    applianceList = (ArrayList<String>) list1;
                    sensorList = (ArrayList<String>) list2;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
            response = "UnknownHostException: " + e.toString();
        } catch (EOFException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            response = "IOException: " + e.toString();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                threadExecProgress = "done";
            }
        }
        return response;
    }

    @Override
    protected void onPostExecute(String result) {
        if(typeOfAction==0) {
            textResponseView.setText(response);
        }
        super.onPostExecute(result);
    }
}