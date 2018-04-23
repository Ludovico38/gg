/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.magazzino.DTO;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author forIT
 */
public class PaymentToSupplierDTO {
    private String fornitore;
    private BigDecimal pagamento;

    public PaymentToSupplierDTO() {
    }

    public PaymentToSupplierDTO(String fornitore, BigDecimal pagamento) {
        this.fornitore = fornitore;
        this.pagamento = pagamento;
    }

    public String getFornitore() {
        return fornitore;
    }

    public void setFornitore(String fornitore) {
        this.fornitore = fornitore;
    }

    public BigDecimal getPagamento() {
        return pagamento;
    }

    public void setPagamento(BigDecimal pagamento) {
        this.pagamento = pagamento;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.fornitore);
        hash = 97 * hash + Objects.hashCode(this.pagamento);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PaymentToSupplierDTO other = (PaymentToSupplierDTO) obj;
        if (!Objects.equals(this.fornitore, other.fornitore)) {
            return false;
        }
        if (!Objects.equals(this.pagamento, other.pagamento)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PaymentToSupplierDTO{" + "fornitore=" + fornitore + ", pagamento=" + pagamento + '}';
    }
    
    
}
