package com.alevel.telegram.bot.service;

import java.util.Date;

public interface BotService {

    StringBuilder listDrugs ();

    StringBuilder listManufacturers ();

    StringBuilder drug (String drugName);

    StringBuilder listDrugsByDateOfApproval (Date from, Date to);
}
