package evgenulianov.cardtransfer.repository;

import evgenulianov.cardtransfer.model.CardTransferTransactional;

public interface RepositoryTransfer {
    void saveTransaction(CardTransferTransactional transactional);
}
