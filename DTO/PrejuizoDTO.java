package DTO;

import java.util.Date;

public class PrejuizoDTO {
    private Date dataCalculo;
    private float prejuizoTotal;

    public PrejuizoDTO(Date dataCalculo, float prejuizoTotal) {
        this.dataCalculo = dataCalculo;
        this.prejuizoTotal = prejuizoTotal;
    }

    public Date getDataCalculo() {
        return dataCalculo;
    }

    public void setDataCalculo(Date dataCalculo) {
        this.dataCalculo = dataCalculo;
    }

    public float getPrejuizoTotal() {
        return prejuizoTotal;
    }

    public void setPrejuizoTotal(float prejuizoTotal) {
        this.prejuizoTotal = prejuizoTotal;
    }
}
