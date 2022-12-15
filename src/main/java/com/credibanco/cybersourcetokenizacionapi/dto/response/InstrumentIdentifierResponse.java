package com.credibanco.cybersourcetokenizacionapi.dto.response;

import com.credibanco.cybersourcetokenizacionapi.model.BillTo;
import com.credibanco.cybersourcetokenizacionapi.model.Card;
import com.credibanco.cybersourcetokenizacionapi.model.Links;
import com.credibanco.cybersourcetokenizacionapi.model.MetaData;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class InstrumentIdentifierResponse {

  @SerializedName("id")
  private Long id;
  @SerializedName("type")
  private String type;
  @SerializedName("card")
  private Card card;
  @SerializedName("billTo")
  private BillTo billTo;
  @SerializedName("object")
  private String object;
  @SerializedName("metadata")
  private MetaData metaData;
  @SerializedName("_links")
  private Links links;
}
