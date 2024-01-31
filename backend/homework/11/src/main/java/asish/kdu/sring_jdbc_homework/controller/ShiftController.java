package asish.kdu.sring_jdbc_homework.controller;

import asish.kdu.sring_jdbc_homework.dtos.ShiftTypeDTO;
import asish.kdu.sring_jdbc_homework.dtos.ShiftsDTO;
import asish.kdu.sring_jdbc_homework.models.ShiftType;
import asish.kdu.sring_jdbc_homework.models.Shifts;
import asish.kdu.sring_jdbc_homework.services.ShiftService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sql.DataSource;
import java.util.List;

@Controller
@Slf4j
public class ShiftController {
    private ShiftService shiftService;

    @Autowired
    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    @GetMapping("/shifttype")
    public ResponseEntity<List<ShiftType>> getShiftType(@RequestParam String tenantId) {
        log.info(tenantId);
        List<ShiftType> shiftTypes = shiftService.getShiftType(tenantId);
        log.info(shiftTypes.toString());
        return new ResponseEntity<>(shiftTypes, HttpStatus.OK);
    }

    @GetMapping("/shifts")
    public ResponseEntity<List<Shifts>> getShifts(@RequestParam String tenantId) {
        log.info(tenantId);
        List<Shifts> shifts = shiftService.getShifts(tenantId);
        log.info(shifts.toString());
        return new ResponseEntity<>(shifts, HttpStatus.OK);
    }

    @PostMapping("/shifttype")
    public ResponseEntity<String> createShiftType(@RequestBody ShiftTypeDTO shiftTypeDTO) {
        shiftService.addShiftType(shiftTypeDTO);
        return new ResponseEntity<>("Shift type created successfully!!", HttpStatus.OK);
    }

    @PostMapping("/shifts")
    public ResponseEntity<String> createShift(@RequestBody ShiftsDTO shiftsDTO) {
        shiftService.addShifts(shiftsDTO);
        return new ResponseEntity<>("Shift created successfully!!", HttpStatus.OK);
    }
}
