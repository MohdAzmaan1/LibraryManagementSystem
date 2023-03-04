package com.example.Library.Management.System.Controllers;

import com.example.Library.Management.System.DTO.IssueBookRequestDTO;
import com.example.Library.Management.System.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/issueBook")
    public String issueBook(@RequestBody IssueBookRequestDTO issueBookRequestDTO){
        try{
            return transactionService.issueBook(issueBookRequestDTO);
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/getTxnInfo")
    public String getTransactionEntry(@RequestParam("bookId")Integer bookId,@RequestParam("cardId")Integer cardId){
        return transactionService.getTransactions(bookId,cardId);
    }

    @PostMapping("/returnBook")
    public String returnBook(@RequestParam("cardId") int cardId, @RequestParam("bookId") int bookId) throws Exception{
        String externalTransactionId = transactionService.returnBook(cardId, bookId);
        return externalTransactionId;
    }
}
