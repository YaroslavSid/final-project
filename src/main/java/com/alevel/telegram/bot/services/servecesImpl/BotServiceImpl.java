package com.alevel.telegram.bot.services.servecesImpl;

import com.alevel.model.Drugs;
import com.alevel.model.Manufacturer;
import com.alevel.service.DrugsService;
import com.alevel.service.ManufacturerService;
import com.alevel.telegram.bot.services.BotService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BotServiceImpl implements BotService {

    DrugsService drugsService;
    ManufacturerService manufacturerService;

    @Override
    public StringBuilder listDrugs() {
        int count = 1;
        List<Drugs> list = drugsService.findAll();
        StringBuilder listDrugs = new StringBuilder();
        listDrugs.append("Drugs:").append("\n");
        for (Drugs drugs : list) {
            listDrugs.append(count).append(") ").append(drugs.getName()).append("\n");
            count++;
        }

        return listDrugs;
    }

    @Override
    public StringBuilder listManufacturers() {
        int count = 1;
        List<Manufacturer> list = manufacturerService.findAll();
        StringBuilder stringManufacturers = new StringBuilder();
        stringManufacturers.append("Manufacturer:").append("\n");
        for (Manufacturer manufacturer : list) {
            stringManufacturers.append(count).append(") ").append(manufacturer.getName()).append("\n");
            count++;
        }
        return stringManufacturers;
    }

    @Override
    public StringBuilder drug(String drugName) {

        Drugs drugFromDB = drugsService.findDrugByName(drugName);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        StringBuilder drug = new StringBuilder();

        if (drugFromDB == null) {
            drug.append("Not found");
        } else {
            drug.append("Name drug: ").append(drugFromDB.getName()).append("\n")
                    .append("Manufacturer: ").append(drugFromDB.getManufacturer().getName()).append("\n")
                    .append("Approval Date: ").append(simpleDateFormat.format(drugFromDB.getDateOfApproval())).append("\n")
                    .append("Treatment for: ").append(drugFromDB.getTreatmentFor()).append("\n")
                    .append("Description for: ").append(drugFromDB.getDescription());
        }
        return drug;
    }

    @Override
    public StringBuilder listDrugsByDateOfApproval(Date from, Date to) {
        List<String> listDrugs = drugsService.findAllDrugsByDateOfApproval(from, to);
        StringBuilder listDrugsByDate = new StringBuilder();
        int count = 1;
        listDrugsByDate.append("List of drugs for the selected time:").append("\n");
        for (String s : listDrugs) {
            listDrugsByDate.append(count).append(") ").append(s).append("\n");
            count++;
        }
        return listDrugsByDate;
    }

}
