package com.credibanco.cybersourcetokenizacionapi.dto.request;

import com.credibanco.cybersourcetokenizacionapi.model.BillTo;
import com.credibanco.cybersourcetokenizacionapi.model.Card;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class InstrumentIdentifierRequest {

  @SerializedName("type")
  private String type;
  @SerializedName("card")
  private Card card;
  @SerializedName("billTo")
  private BillTo billTo;

}
