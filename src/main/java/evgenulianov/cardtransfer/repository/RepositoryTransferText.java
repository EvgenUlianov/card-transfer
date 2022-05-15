package evgenulianov.cardtransfer.repository;


import evgenulianov.cardtransfer.model.CardTransferTransactional;
import org.springframework.stereotype.Repository;
import org.webjars.NotFoundException;

import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class RepositoryTransferText implements RepositoryTransfer{
    private static final String FILE_NAME = "data.txt";


    @Override
    public void saveTransaction(CardTransferTransactional transactional) {

        final  Writer writer;
        boolean fileDoesntExist;
        File file = new File(FILE_NAME);
        fileDoesntExist = !file.exists();

        try {
            writer = new FileWriter(FILE_NAME, true);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        final BufferedWriter bufferWriter = new BufferedWriter(writer);

        try (writer; bufferWriter) {
            if(fileDoesntExist)
                bufferWriter.write(getHeader());
            bufferWriter.write(getRow(transactional));

        }
        catch (IOException e) {
            System.out.println(e);
            return;
        }
    }


    private String getHeader(){
        StringBuilder builder = new StringBuilder();
        String delimiter = ", ";
        builder.append("date");
        builder.append(delimiter);
        builder.append("cardFromNumber");
        builder.append(delimiter);
        builder.append("cardToNumber");
        builder.append(delimiter);
        builder.append("currency");
        builder.append(delimiter);
        builder.append("value");
        builder.append("\n");
        return builder.toString();
    }

    private String getRow(CardTransferTransactional transactional){
        StringBuilder builder = new StringBuilder();
        String delimiter = ", ";
        builder.append(transactional.getDate().toString());
        builder.append(delimiter);
        builder.append(transactional.getCardFromNumber());
        builder.append(delimiter);
        builder.append(transactional.getCardToNumber());
        builder.append(delimiter);
        builder.append(transactional.getCurrency());
        builder.append(delimiter);
        builder.append(Integer.toString(transactional.getValue()));
        builder.append("\n");
        return builder.toString();
    }

}
