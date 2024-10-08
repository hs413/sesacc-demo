package sesac.sesaccdemo.campus.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import sesac.sesaccdemo.campus.dto.CampusResponse;
import sesac.sesaccdemo.campus.entity.Campus;
import sesac.sesaccdemo.campus.repository.CampusRepository;

@Service
@Log4j2
@RequiredArgsConstructor
public class CampusService {

    private final CampusRepository campusRepository;

    public List<CampusResponse> findAll() {
        List<Campus> list = campusRepository.findAll();

        List<CampusResponse> response =
                list.stream().map(r -> campusToResponse(r)).toList();

        return response;
    }

    private CampusResponse campusToResponse(Campus campus) {
        return new CampusResponse(campus.getId(), campus.getName());
    }
}
