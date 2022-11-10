package MOCUMOCU.project.owner.service;

import MOCUMOCU.project.customer.entity.Customer;
import MOCUMOCU.project.owner.entity.Owner;
import MOCUMOCU.project.owner.form.OwnerInfoDTO;
import MOCUMOCU.project.owner.form.OwnerLoginDTO;
import MOCUMOCU.project.owner.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Override
    public Long join(Owner owner) {
        ownerRepository.save(owner);
        return owner.getId();
    }

    @Override
    public void withdrawal(Long id) {
        Owner findOwner = ownerRepository.findOne(id);
        ownerRepository.remove(findOwner);
    }

    @Override
    public void updatePhoneNum(Long id, String phoneNum) {
        Owner findOwner = ownerRepository.findOne(id);
        findOwner.getPrivacy().setPhoneNum(phoneNum);
    }

    @Override
    public void updatePassword(Long id, String password) {
        Owner findOwner = ownerRepository.findOne(id);
        findOwner.getPrivacy().setPassword(password);
    }

    @Override
    public boolean authPassword(String password, Long id) {
        Owner findOwner = ownerRepository.findOne(id);

        return findOwner.getPrivacy().getPassword().equals(password);

    }

    @Override
    @Transactional(readOnly = true)
    public boolean login(OwnerLoginDTO ownerLoginDTO) {
        List<Owner> findOwner = ownerRepository.findByEmail(ownerLoginDTO.getOwnerEmail());

        if (findOwner.isEmpty()) {
           log.info("empty");
            return false;
        } else {
            log.info("in");
            return findOwner.get(0).getPrivacy().getPassword().equals(ownerLoginDTO.getOwnerPassword());
        }
    }

    @Override
    public OwnerInfoDTO findOwnerByEmail(String email) {
        List<Owner> findOwners = ownerRepository.findByEmail(email);

        Owner findOwner = findOwners.get(0);
        OwnerInfoDTO ownerInfoDTO = new OwnerInfoDTO();
        ownerInfoDTO.setOwnerId(findOwner.getId());
        ownerInfoDTO.setOwnerName(findOwner.getPrivacy().getName());
        ownerInfoDTO.setOwnerEmail(findOwner.getPrivacy().getEmail());
        ownerInfoDTO.setLogIn(true);
        ownerInfoDTO.setUserType("Owner");
        return ownerInfoDTO;
    }

    @Override
    public String findEmail(String ownerName, String phoneNum) {
        List<Owner> findOwner = ownerRepository.findByPhoneNum(phoneNum);

        if (findOwner.isEmpty()) {
            return null;
        } else{
            return findOwner.get(0).getPrivacy().getEmail();
        }
    }

    @Override
    public String findPassword(String email) {
        List<Owner> findOwner = ownerRepository.findByEmail(email);

        if (findOwner.isEmpty()) {
            return null;
        } else{
            return findOwner.get(0).getPrivacy().getPassword();
        }
    }
}
