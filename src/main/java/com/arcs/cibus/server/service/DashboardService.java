package com.arcs.cibus.server.service;

import com.arcs.cibus.server.domain.Category;
import com.arcs.cibus.server.domain.Dashboard;
import com.arcs.cibus.server.domain.Sale;
import com.arcs.cibus.server.domain.enums.DashboardPeriod;
import com.arcs.cibus.server.domain.enums.SaleStatus;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.repository.CategoryRepository;
import com.arcs.cibus.server.repository.SaleRepository;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;
import lombok.Builder;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;


@Service
public class DashboardService {

    @Autowired
    private SaleRepository saleRepository;

    public Dashboard getData(DashboardPeriod period) {
        Date dateInitial = defineInitialDate(period);
        Date dateFinal = defineFinalDate(period);

        List<Sale> sales = saleRepository.findAllByPeriod(dateInitial, dateFinal);
        Dashboard dashboard = Dashboard.builder()
                .salesTotal(new BigDecimal(0))
                .ordersClosed(0)
                .ordersOpenned(0)
                .build();

        for (Sale sale : sales) {
            BigDecimal pricaTotal = dashboard.getSalesTotal().add(sale.getPrice().multiply(new BigDecimal(sale.getQuantity())));
            dashboard.setSalesTotal(pricaTotal);

            if (sale.getSaleStatus().equals(SaleStatus.ORDER)) {
                dashboard.setOrdersOpenned(dashboard.getOrdersOpenned() + 1);
            }

            if (sale.getSaleStatus().equals(SaleStatus.PAID)) {
                dashboard.setOrdersClosed(dashboard.getOrdersClosed() + 1);
            }

            if (dashboard.getQuantityProducts().containsKey(sale.getProduct().getName())) {
                dashboard.getQuantityProducts().put(sale.getProduct().getName(), sale.getQuantity() + dashboard.getQuantityProducts().get(sale.getProduct().getName()).longValue());
            } else {
                dashboard.getQuantityProducts().put(sale.getProduct().getName(), sale.getQuantity());
            }

            for (Category category : sale.getProduct().getCategorys()) {
                if (dashboard.getPercentCategories().containsKey(category.getName())) {
                    dashboard.getPercentCategories().put(category.getName(), sale.getQuantity() + dashboard.getPercentCategories().get(category.getName()).doubleValue());
                } else {
                    dashboard.getPercentCategories().put(category.getName(), sale.getQuantity().doubleValue());
                }
            }
        }

        updateCategoriesPercent(dashboard);

        return dashboard;
    }

    private void updateCategoriesPercent(Dashboard dashboard) {
        int totalOrders = dashboard.getOrdersClosed() + dashboard.getOrdersOpenned();

        for (Map.Entry<String, Double> t : dashboard.getPercentCategories().entrySet()) {
            Double quantity = t.getValue() / totalOrders;
            dashboard.getPercentCategories().put(t.getKey(), quantity);
        }
    }

    private Date defineInitialDate(DashboardPeriod period) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setFirstDayOfWeek(Calendar.SUNDAY);
            calendar.setTime(new Date());

            int DAY = calendar.get(Calendar.DAY_OF_MONTH);
            int INITIAL_DAY_WEEK = DAY - calendar.get(Calendar.DAY_OF_WEEK);
            int MOUNTH_NUMBER = calendar.get(Calendar.MONDAY) + 1;
            int YEAR = calendar.get(Calendar.YEAR);

            String MOUNTH = MOUNTH_NUMBER < 10 ? "0" + MOUNTH_NUMBER : MOUNTH_NUMBER + "";
            String dateInitial = "";

            switch (period) {
                case DAY:
                    dateInitial = YEAR + "-" + MOUNTH + "-" + DAY + " 00:00:00";
                    break;
                case WEEK:
                    dateInitial = YEAR + "-" + MOUNTH + "-" + INITIAL_DAY_WEEK + " 00:00:00";
                    break;
                case MOUNTH:
                    dateInitial = YEAR + "-" + MOUNTH + "-" + "01" + " 00:00:00";
                    break;
            }

            return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dateInitial);
        }
        catch (Exception e){
            return new Date();
        }
    }

    private Date defineFinalDate(DashboardPeriod period) {
        try {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(new Date());

        int DAY = calendar.get(Calendar.DAY_OF_MONTH);
        int FINAL_DAY_WEEK = DAY + (7 - calendar.get(Calendar.DAY_OF_WEEK));
        int FINAL_DAY_MOUNTH =  calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int MOUNTH_NUMBER = calendar.get(Calendar.MONDAY) + 1;
        int YEAR = calendar.get(Calendar.YEAR);

        String MOUNTH = MOUNTH_NUMBER < 10 ? "0" + MOUNTH_NUMBER : MOUNTH_NUMBER + "";
        String dateFinal = "";

        switch (period) {
            case DAY:
                dateFinal = YEAR + "-" + MOUNTH + "-" + DAY + " 23:59:59";
                break;
            case WEEK:
                dateFinal = YEAR + "-" + MOUNTH + "-" + FINAL_DAY_WEEK + " 23:59:59";
                break;
            case MOUNTH:
                dateFinal = YEAR + "-" + MOUNTH + "-" + FINAL_DAY_MOUNTH + " 23:59:59";
                break;
        }

            return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dateFinal);
        }
        catch (Exception e){
            return new Date();
        }
    }
}
