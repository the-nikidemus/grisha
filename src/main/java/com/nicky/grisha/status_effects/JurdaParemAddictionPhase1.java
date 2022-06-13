package com.nicky.grisha.status_effects;

import com.nicky.grisha.Grisha;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;

public class JurdaParemAddictionPhase1 extends StatusEffect{
	
	//Length of phase 1
	public static int LENGTH = 2400;
	
	public JurdaParemAddictionPhase1() {
	    super(
				StatusEffectCategory.HARMFUL, // whether beneficial or harmful for entities
	      0xbe5050); // color in RGB
	  }
	
	// This method is called every tick to check whether it should apply the status effect or not
	  @Override
	  public boolean canApplyUpdateEffect(int duration, int amplifier) {
	    // In our case, we just make it return true so that it applies the status effect every tick.
	    return duration == 1;
	  }
	  
	  @Override
	  public void applyUpdateEffect(LivingEntity entity, int amplifier) {
	    if (entity instanceof PlayerEntity) {
	    	((PlayerEntity) entity).addStatusEffect(new StatusEffectInstance(Grisha.JURDA_PAREM_ADDICTION_PHASE2,JurdaParemAddictionPhase2.LENGTH,0,false,false,true)); 
	    	((PlayerEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER,JurdaParemAddictionPhase2.LENGTH,0,false,false,false));
	    	((PlayerEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA,JurdaParemAddictionPhase2.LENGTH,0,false,false,false));
	    	((PlayerEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.POISON,JurdaParemAddictionPhase2.LENGTH,0,false,false,false));
	    }
	  }

}
