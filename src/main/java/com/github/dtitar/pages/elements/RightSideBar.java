package com.github.dtitar.pages.elements;

import lombok.Getter;

import static com.codeborne.selenide.Selenide.page;

@Getter
public class RightSideBar {

   private BetSlip betSlip;

   public RightSideBar() {
      this.betSlip = page(BetSlip.class);
   }
}
