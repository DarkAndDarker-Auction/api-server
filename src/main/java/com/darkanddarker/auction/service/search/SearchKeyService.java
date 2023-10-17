package com.darkanddarker.auction.service.search;

import com.darkanddarker.auction.dto.search.*;
import com.darkanddarker.auction.model.searchKey.*;
import com.darkanddarker.auction.repository.searchKey.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchKeyService {

    private final ArmorTypeRepository armorTypeRepository;
    private final HandTypeRepository handTypeRepository;
    private final WeaponTypeRepository weaponTypeRepository;
    private final SlotTypeRepository slotTypeRepository;
    private final RarityRepository rarityRepository;

    public AllSearchKeyResponseDto getAllSearchKeys() {
        return AllSearchKeyResponseDto.builder()
                .armorTypes(armorTypeRepository.findAll())
                .handTypes(handTypeRepository.findAll())
                .weaponTypes(weaponTypeRepository.findAll())
                .slotTypes(slotTypeRepository.findAll())
                .rarities(rarityRepository.findAll())
                .build();
    }

    public HandTypeResponseDto getHandTypes() {
        List<HandType> handTypes = handTypeRepository.findAll();
        return new HandTypeResponseDto(handTypes);
    }

    public WeaponTypeResponseDto getWeaponTypes() {
        List<WeaponType> weaponTypes = weaponTypeRepository.findAll();
        return new WeaponTypeResponseDto(weaponTypes);
    }

    public ArmorTypeResponseDto getArmorTypes() {
        List<ArmorType> armorTypes = armorTypeRepository.findAll();
        return new ArmorTypeResponseDto(armorTypes);
    }

    public SlotTypeResponseDto getSlotTypes() {
        List<SlotType> slotTypes = slotTypeRepository.findAll();
        return new SlotTypeResponseDto(slotTypes);
    }

    public RarityResponseDto getRarities() {
        List<Rarity> rarities = rarityRepository.findAll();
        return new RarityResponseDto(rarities);
    }

}
