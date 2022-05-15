package evgenulianov.cardtransfer.service;

import evgenulianov.cardtransfer.model.CardTransferTransactional;
import evgenulianov.cardtransfer.repository.RepositoryTransfer;
import evgenulianov.cardtransfer.requests.RequestCardToCardTransfer;
import evgenulianov.cardtransfer.requests.RequestConfirmOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Log4j2
public class ServiceTransfer {

    final RepositoryTransfer repositoryTransfer;
    private final Map<Integer, CardTransferTransactional> openedTransactions;
    private AtomicInteger currentId;

    public ServiceTransfer(RepositoryTransfer repositoryTransfer) {
        this.repositoryTransfer = repositoryTransfer;
        openedTransactions = new ConcurrentSkipListMap<>();
        currentId = new AtomicInteger(0);
    }

    public int openTransaction(RequestCardToCardTransfer request){
        int id = currentId.incrementAndGet();
        openedTransactions.put(id, new CardTransferTransactional(request));
        return id;
    }

    public int commitTransaction(RequestConfirmOperation request) throws NumberFormatException, NotFoundException {
        int id = Integer.parseInt(request.getOperationId());
        CardTransferTransactional transactional = openedTransactions.get(id);
        if (transactional == null)
            throw new NotFoundException("transactional has not found");
        repositoryTransfer.saveTransaction(transactional);
        openedTransactions.remove(id);
        return id;
    }

}
